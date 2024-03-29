package com.hll.chat.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.hll.chat.DemoHelper;
import com.hll.chat.DemoModel;
import com.hll.chat.R;
import com.hll.chat.ui.BaseActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMPushConfigs;
import com.hyphenate.easeui.widget.EaseSwitchButton;
import com.hyphenate.easeui.widget.EaseTitleBar;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by wei on 2016/12/6.
 */

public class OfflinePushSettingsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener{
    private CheckBox noDisturbOn, noDisturbOff, noDisturbInNight;
    private EaseSwitchButton useFCMSwitch;
    private Status status = Status.OFF;

    EMPushConfigs mPushConfigs;
    DemoModel settingsModel;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_offline_push_settings);

        EaseTitleBar titleBar = (EaseTitleBar) findViewById(R.id.title_bar);
        noDisturbOn = (CheckBox) findViewById(R.id.cb_no_disturb_on);
        noDisturbOff = (CheckBox) findViewById(R.id.cb_no_disturb_off);
        noDisturbInNight = (CheckBox) findViewById(R.id.cb_no_disturb_only_night);
        useFCMSwitch = (EaseSwitchButton) findViewById(R.id.switch_use_fcm);
        Button saveButton = (Button) findViewById(R.id.btn_save);

        noDisturbOn.setOnCheckedChangeListener(this);
        noDisturbOff.setOnCheckedChangeListener(this);
        noDisturbInNight.setOnCheckedChangeListener(this);

        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog savingPd = new ProgressDialog(OfflinePushSettingsActivity.this);
                savingPd.setMessage(getString(R.string.push_saving_settings));
                savingPd.setCanceledOnTouchOutside(false);
                savingPd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(status == Status.ON){
                                EMClient.getInstance().pushManager().disableOfflinePush(0, 24);
                            }else if(status == Status.OFF){
                                EMClient.getInstance().pushManager().enableOfflinePush();
                            }else{
                                EMClient.getInstance().pushManager().disableOfflinePush(22, 7);
                            }
                            finish();
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    savingPd.dismiss();
                                    Toast.makeText(OfflinePushSettingsActivity.this, R.string.push_save_failed, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        settingsModel = DemoHelper.getInstance().getModel();
        if (settingsModel.isUseFCM()) {
            useFCMSwitch.openSwitch();
        } else {
            useFCMSwitch.closeSwitch();
        }
        useFCMSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (useFCMSwitch.isSwitchOpen()) {
                    useFCMSwitch.closeSwitch();
                    settingsModel.setUseFCM(false);
                    EMClient.getInstance().getOptions().setUseFCM(false);
                } else {
                    useFCMSwitch.openSwitch();
                    settingsModel.setUseFCM(true);
                    EMClient.getInstance().getOptions().setUseFCM(true);
                }
            }
        });

        mPushConfigs = EMClient.getInstance().pushManager().getPushConfigs();
        if(mPushConfigs == null){
            final ProgressDialog loadingPd = new ProgressDialog(this);
            loadingPd.setMessage("loading");
            loadingPd.setCanceledOnTouchOutside(false);
            loadingPd.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mPushConfigs = EMClient.getInstance().pushManager().getPushConfigsFromServer();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadingPd.dismiss();
                                processPushConfigs();
                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadingPd.dismiss();
                                Toast.makeText(OfflinePushSettingsActivity.this, "loading failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }else{
            processPushConfigs();
        }


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int i = buttonView.getId();
        if (i == R.id.cb_no_disturb_on) {
            if (isChecked) {
                noDisturbOff.setChecked(false);
                noDisturbInNight.setChecked(false);
                status = Status.ON;
            }
        } else if (i == R.id.cb_no_disturb_off) {
            if (isChecked) {
                noDisturbOn.setChecked(false);
                noDisturbInNight.setChecked(false);
                status = Status.OFF;
            }
        } else if (i == R.id.cb_no_disturb_only_night) {
            if (isChecked) {
                noDisturbOn.setChecked(false);
                noDisturbOff.setChecked(false);
                status = Status.ON_IN_NIGHT;
            }
        }
    }

    private void processPushConfigs(){
        if(mPushConfigs == null)
            return;
        if(mPushConfigs.isNoDisturbOn()){
            status = status.ON;
            noDisturbOn.setChecked(true);
            if(mPushConfigs.getNoDisturbStartHour() > 0){
                status = Status.ON_IN_NIGHT;
                noDisturbInNight.setChecked(true);
            }
        }else{
            status = Status.OFF;
            noDisturbOff.setChecked(true);
        }
    }

    private enum Status {
        ON,
        OFF,
        ON_IN_NIGHT
    }

}
