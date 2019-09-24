package com.wil.user.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.wil.user.di.component.DaggerSettingsComponent
import com.wil.user.mvp.contract.SettingsContract
import com.wil.user.mvp.presenter.SettingsPresenter

import com.wil.user.R
import me.jessyan.armscomponent.commonsdk.core.RouterHub

import com.jess.arms.utils.Preconditions.checkNotNull
import kotlinx.android.synthetic.main.setting_activity_home.*
import kotlinx.android.synthetic.main.setting_item.view.*


/**
 * Desc:
 *
 *
 * Date: 2019-08-15
 * Copyright: Copyright (c) 2018-2019
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [pengysh]
 */
@Route(path = RouterHub.SETTING_HOME)
class SettingsActivity : BaseActivity<SettingsPresenter>(), SettingsContract.View {
    var mSourceIcon: IntArray = intArrayOf()
    var mSourceText: Array<String> = arrayOf()
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerSettingsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.setting_activity_home //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    override fun initData(savedInstanceState: Bundle?) {
        mSourceIcon = resources.getIntArray(R.array.setting_icon)
        mSourceText = resources.getStringArray(R.array.setting_name)
        launcher_country_recycle.layoutManager = LinearLayoutManager(this)
        launcher_country_recycle.adapter = RecycleAdapter()
    }

    inner class RecycleAdapter : RecyclerView.Adapter<RecycleViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.setting_item, null)
            return RecycleViewHolder(view)
        }

        override fun getItemCount(): Int {
            return this@SettingsActivity.mSourceIcon.size
        }

        override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
            holder.textView.setting_item_icon.setBackgroundResource(resources.obtainTypedArray(R.array.setting_icon).getResourceId(position,0))
            holder.textView.setting_item_tv.text = this@SettingsActivity.mSourceText[position]
        }

    }

    class RecycleViewHolder(val textView: View) : RecyclerView.ViewHolder(textView)


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
