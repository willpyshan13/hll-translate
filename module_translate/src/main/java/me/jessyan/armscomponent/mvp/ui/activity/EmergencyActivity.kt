package me.jessyan.armscomponent.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import me.jessyan.armscomponent.R

import me.jessyan.armscomponent.di.component.DaggerEmergencyComponent
import me.jessyan.armscomponent.di.module.EmergencyModule
import me.jessyan.armscomponent.mvp.contract.EmergencyContract
import me.jessyan.armscomponent.mvp.presenter.EmergencyPresenter

/**
 * Desc:
 * <p>
 * Created by will on 08/16/2019 14:50
 * Copyright: Copyright (c) 2018-2019
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [pengyushan]
 */
class EmergencyActivity : BaseActivity<EmergencyPresenter>(), EmergencyContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEmergencyComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .emergencyModule(EmergencyModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_emergency //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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
