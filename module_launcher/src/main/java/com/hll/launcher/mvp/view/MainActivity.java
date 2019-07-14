package com.hll.launcher.mvp.view;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hll.launcher.R;
import com.hll.launcher.di.component.DaggerLauncherComponent;
import com.hll.launcher.mvp.contract.LauncherContract;
import com.hll.launcher.mvp.presenter.LauncherPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import me.jessyan.armscomponent.commonsdk.core.RouterHub;

@Route(path = RouterHub.ZHIHU_DETAILACTIVITY)
public class MainActivity extends BaseActivity<LauncherPresenter> implements LauncherContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLauncherComponent.builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.launcher_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void shonContent() {

    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
