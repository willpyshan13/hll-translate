package me.jessyan.armscomponent.hotspot.mvp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.kyleduo.switchbutton.SwitchButton;
import com.luck.picture.lib.tools.ToastManage;
import com.wil.user.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import me.jessyan.armscomponent.hotspot.constant.HotSpotConstant;
import me.jessyan.armscomponent.hotspot.di.component.DaggerHotspotActivityComponent;
import me.jessyan.armscomponent.hotspot.mvp.contract.HotspotContract;
import me.jessyan.armscomponent.hotspot.mvp.presenter.HotspotPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Desc:个人热点页面
 * <p>
 * Date: 2019-08-16
 * Copyright: Copyright (c) 2010-2019
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [zhuanghongzhan]
 */
@Route(path = RouterHub.HOTSPOT_HOME)
public class HotspotActivity extends BaseActivity<HotspotPresenter> implements HotspotContract.View, View.OnClickListener {

    private TextView tv_name;
    private TextView tv_pwd;
    private SwitchButton switchButton;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHotspotActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_hotspot;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tv_name = this.findViewById(R.id.hotspot_item_name_tv_info);
        tv_pwd = this.findViewById(R.id.hotspot_item_pwd_tv_info);
        switchButton = this.findViewById(R.id.hotspot_item_hotspot_sw);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.hotspot_iv_back) {
            onBackPressed();
        } else if (view.getId() == R.id.hotspot_item_name_tv_info) {
            showInputDialog(HotSpotConstant.DIALOG_INPUT_NAME);
        } else if (view.getId() == R.id.hotspot_item_pwd_tv_info) {
            showInputDialog(HotSpotConstant.DIALOG_INPUT_PWD);
        }
    }


    /**
     * Desc: 显示输入对话框，
     * <p>
     * Author: [zhuanghongzhan]
     * Date: 2019-08-16
     *
     * @param type 类型,有热点名称类型和热点密码类型
     */
    public void showInputDialog(int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(type == HotSpotConstant.DIALOG_INPUT_NAME ? "请输入热点名称" : "请输入热点密码");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_input_layout, null);
        builder.setView(view);
        EditText editText = view.findViewById(R.id.input_et);
        editText.setInputType(type == HotSpotConstant.DIALOG_INPUT_NAME ? InputType.TYPE_CLASS_TEXT : InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        builder.setPositiveButton("确定", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positionButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (type == HotSpotConstant.DIALOG_INPUT_NAME) {
                            tv_name.setText(editText.getText());
                            alertDialog.dismiss();
                            alertDialog.setOnShowListener(null);
                        } else if (type == HotSpotConstant.DIALOG_INPUT_PWD) {
                            if(editText.getText().toString().length()<8){
                                ToastManage.s(getApplication(),"密码长度不能小于8位");
                            }else{
                                tv_pwd.setText(editText.getText());
                                alertDialog.dismiss();
                                alertDialog.setOnShowListener(null);
                            }
                        }
                    }
                });

            }
        });
        alertDialog.show();
    }

}
