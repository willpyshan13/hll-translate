package me.jessyan.armscomponent.hotspot.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.wil.user.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;
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
        }
    }
}
