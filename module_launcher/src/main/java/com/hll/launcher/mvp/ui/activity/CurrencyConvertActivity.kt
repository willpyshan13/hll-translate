package com.hll.launcher.mvp.ui.activity

import android.content.Intent
import android.os.Bundle


import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.hll.launcher.di.component.DaggerCurrencyConvertComponent
import com.hll.launcher.mvp.contract.CurrencyConvertContract
import com.hll.launcher.mvp.presenter.CurrencyConvertPresenter

import com.hll.launcher.R


import com.jess.arms.utils.Preconditions.checkNotNull


/**
 * Desc:
 *
 *
 * Created by will on 08/16/2019 11:26
 * Copyright: Copyright (c) 2018-2019
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [pengyushan]
 */
class CurrencyConvertActivity : BaseActivity<CurrencyConvertPresenter>(), CurrencyConvertContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCurrencyConvertComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.launcher_activity_currency_convert
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        checkNotNull(message)
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        checkNotNull(intent)
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}
