package com.hll.launcher.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.hll.launcher.di.component.DaggerCountrySelectComponent
import com.hll.launcher.di.module.CountrySelectModule
import com.hll.launcher.mvp.contract.CountrySelectContract
import com.hll.launcher.mvp.presenter.CountrySelectPresenter

import com.hll.launcher.R


/**
 * Desc:
 * <p>
 * Created by will on 09/23/2019 22:44
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: pengyushan
 */
class CountrySelectActivity : BaseActivity<CountrySelectPresenter>(), CountrySelectContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerCountrySelectComponent
                .builder()
                .appComponent(appComponent)
                .countrySelectModule(CountrySelectModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_country_select
    }


    override fun initData(savedInstanceState: Bundle?) {

    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
}
