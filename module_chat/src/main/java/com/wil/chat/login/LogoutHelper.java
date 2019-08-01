package com.wil.chat.login;

import com.netease.nim.uikit.api.NimUIKit;
import com.wil.chat.DemoCache;
import com.wil.chat.redpacket.NIMRedPacketClient;

/**
 * 注销帮助类
 * Created by huangjun on 2015/10/8.
 */
public class LogoutHelper {
    public static void logout() {
        // 清理缓存&注销监听&清除状态
        NimUIKit.logout();
        DemoCache.clear();
        NIMRedPacketClient.clear();
    }
}
