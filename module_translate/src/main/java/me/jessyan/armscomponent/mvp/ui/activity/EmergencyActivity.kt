package me.jessyan.armscomponent.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import me.jessyan.armscomponent.di.component.DaggerEmergencyComponent
import me.jessyan.armscomponent.di.module.EmergencyModule
import me.jessyan.armscomponent.mvp.contract.EmergencyContract
import me.jessyan.armscomponent.mvp.presenter.EmergencyPresenter
import me.jessyan.armscomponent.R
import kotlinx.android.synthetic.main.translate_activity_emergency.*
import me.jessyan.armscomponent.commonsdk.core.RouterHub

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
@Route(path = RouterHub.LAUNCHER_EMERGENCY)
class EmergencyActivity : BaseActivity<EmergencyPresenter>(), EmergencyContract.View {
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.translate_recycleview) }
    private val title: TextView by lazy { findViewById<TextView>(R.id.public_toolbar_title) }
    private val translateCountry: TextView by lazy { findViewById<TextView>(R.id.translate_country) }
    private val translatePolice: TextView by lazy { findViewById<TextView>(R.id.translate_police) }
    private val translatePoliceNum: TextView by lazy { findViewById<TextView>(R.id.translate_police_num) }
    private val translateRecycleview: RecyclerView by lazy { findViewById<RecyclerView>(R.id.translate_recycleview) }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEmergencyComponent
                .builder()
                .appComponent(appComponent)
                .emergencyModule(EmergencyModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.translate_activity_emergency
    }


    override fun initData(savedInstanceState: Bundle?) {
        title.text = "紧急报警"
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
