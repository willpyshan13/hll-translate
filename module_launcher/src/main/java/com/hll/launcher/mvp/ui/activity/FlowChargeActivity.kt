package com.hll.launcher.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.hll.launcher.di.component.DaggerFlowChargeComponent
import com.hll.launcher.mvp.contract.FlowChargeContract
import com.hll.launcher.mvp.presenter.FlowChargePresenter

import com.hll.launcher.R


import me.jessyan.armscomponent.commonsdk.core.RouterHub

import com.jess.arms.utils.Preconditions.checkNotNull


/**
 * Desc:
 *
 *
 * Date: 2019-08-16
 * Copyright: Copyright (c) 2018-2019
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [pengyushan]
 */
class FlowChargeActivity : BaseActivity<FlowChargePresenter>(), FlowChargeContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerFlowChargeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.launcher_activity_flow_charge //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
