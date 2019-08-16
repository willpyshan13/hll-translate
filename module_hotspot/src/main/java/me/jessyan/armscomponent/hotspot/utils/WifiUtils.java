package me.jessyan.armscomponent.hotspot.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Desc:
 * <p>
 * Date: 2019/8/16
 * Copyright: Copyright (c) 2010-2019
 * Company: @微微科技有限公司
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [zhuanghongzhan]
 */
public class WifiUtils {

    private WifiManager wifiManager;
    private ConnectivityManager connManager;

    public WifiUtils(Context context) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        connManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }


    private boolean isWifiEnabled() {
        return wifiManager.isWifiEnabled();
    }

    /**
     * 是否打开共享热点
     */
    public boolean isWifiApEnabled() {
        try {
            Method method = wifiManager.getClass().getMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (Boolean) method.invoke(wifiManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否打开指定共享热点：名称和密码
     */
    private boolean isWifiApEnabled(String ssid, String password) {
        boolean ret = isWifiApEnabled();
        if (!ret) {
            return false;
        }

        WifiConfiguration wifiConfig = getWifiApConfiguration();
        String apName = wifiConfig.SSID;
        String apPassword = wifiConfig.preSharedKey;
        // TODO 可能要注意引号等特殊字符
        return ssid.equals(apName) && password.equals(apPassword);
    }

    /**
     * 获取AP热点配置信息
     */
    private WifiConfiguration getWifiApConfiguration() {
        try {
            Method method = wifiManager.getClass().getMethod("getWifiApConfiguration");
            method.setAccessible(true);
            return (WifiConfiguration) method.invoke(wifiManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private WifiConfiguration createWifiApConfiguration(String ssid, String password) {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = ssid;// 热点名称
        wifiConfig.preSharedKey = password;// 热点密码
        wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        /**
         * WPA2_PSK=4，比WPA安全性更高
         * 在android 4.4 上设置，无效：Invalid WifiConfiguration
         * 在android 6   上设置，有效
         */
        // wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA2_PSK);
        wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        return wifiConfig;
    }

    private boolean setWifiApConfiguration(WifiConfiguration wifiConfig) {
        try {
            Method method = wifiManager.getClass().getMethod("setWifiApConfiguration", WifiConfiguration.class);
            return (Boolean) method.invoke(wifiManager, wifiConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean setWifiApEnabled(WifiConfiguration wifiConfig, boolean enabled) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                Field iConnMgrField = connManager.getClass().getDeclaredField("mService");
                iConnMgrField.setAccessible(true);
                Object iConnMgr = iConnMgrField.get(connManager);
                Class<?> iConnMgrClass = Class.forName(iConnMgr.getClass().getName());
                Method startTethering = iConnMgrClass.getMethod("startTethering", int.class, ResultReceiver.class, boolean.class);
                startTethering.invoke(iConnMgr, 0, new ResultReceiver(new Handler()) {
                    @Override
                    protected void onReceiveResult(int resultCode, Bundle resultData) {
                        super.onReceiveResult(resultCode, resultData);
                    }
                }, true);
                return true;

            } else {
                Method method = wifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
                return (Boolean) method.invoke(wifiManager, wifiConfig, enabled);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 打开热点
     */
    public void openAp(String apSsid, String apPassword) {
        if (isWifiApEnabled(apSsid, apPassword)) {
            // 1.指定ap已经打开，名称和密码一致
            return;
        } else {
            // 2.指定ap没有打开
            // 2.1.关闭热点
            if (isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }

            // 【注意：如果已经打开ap热点，名称和密码不一致，则需要先关闭AP热点，重新设置AP配置，然后再次打开AP热点】
            // 2.2.关闭ap热点
            WifiConfiguration wifiConfig = createWifiApConfiguration(apSsid, apPassword);
            if (isWifiApEnabled()) {
                setWifiApEnabled(wifiConfig, false);
            }

            // 2.3.重新设置ap配置
            setWifiApConfiguration(wifiConfig);

            // 2.4.打开ap热点
            setWifiApEnabled(wifiConfig, true);
        }
    }

    public void closeAp(String apSsid, String apPassword) {
        // TODO
        WifiConfiguration wifiConfig = createWifiApConfiguration(apSsid, apPassword);
        setWifiApEnabled(wifiConfig, false);
    }

    public void test() {
        String apSsid = "test";
        String apPassword = "12345678";
        openAp(apSsid, apPassword);
        closeAp(apSsid, apPassword);
    }

}
