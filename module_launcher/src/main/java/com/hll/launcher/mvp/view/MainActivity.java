package com.hll.launcher.mvp.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hll.launcher.R;
import com.hll.launcher.mvp.presenter.LauncherPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import me.jessyan.armscomponent.commonsdk.core.RouterHub;

@Route(path = RouterHub.ZHIHU_DETAILACTIVITY)
public class MainActivity extends BaseActivity<LauncherPresenter> {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
//        DaggerLauncher
//                .appComponent(appComponent)
//                .view(this)
//                .build()
//                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
