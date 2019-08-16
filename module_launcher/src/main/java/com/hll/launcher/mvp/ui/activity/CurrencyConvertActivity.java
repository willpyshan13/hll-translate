package com.hll.launcher.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.hll.launcher.di.component.DaggerCurrencyConvertComponent;
import com.hll.launcher.mvp.contract.CurrencyConvertContract;
import com.hll.launcher.mvp.presenter.CurrencyConvertPresenter;

import com.hll.launcher.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Desc:
 * <p>
 * Created by will on 08/16/2019 11:26
 * Copyright: Copyright (c) 2018-2019
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [pengyushan]
 */
public class CurrencyConvertActivity extends BaseActivity<CurrencyConvertPresenter> implements CurrencyConvertContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerCurrencyConvertComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.launcher_activity_currency_convert;
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
}
