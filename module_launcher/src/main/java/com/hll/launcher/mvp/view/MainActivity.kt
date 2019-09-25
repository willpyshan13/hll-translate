package com.hll.launcher.mvp.view

import android.app.Activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.alibaba.android.arouter.facade.annotation.Route
import com.hll.launcher.R
import com.hll.launcher.constant.LauncherConstant
import com.hll.launcher.di.component.DaggerLauncherComponent
import com.hll.launcher.mvp.contract.LauncherContract
import com.hll.launcher.mvp.presenter.LauncherPresenter
import com.hll.launcher.mvp.ui.activity.CountrySelectActivity
import com.hll.launcher.mvp.ui.activity.CurrencyConvertActivity
import com.hll.launcher.mvp.ui.activity.FlowChargeActivity
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.LogUtils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.launcher_country_select.*
import kotlinx.android.synthetic.main.launcher_main.*

import me.jessyan.armscomponent.commonsdk.core.RouterHub
import me.jessyan.armscomponent.commonsdk.utils.TimeUtils
import me.jessyan.armscomponent.commonsdk.utils.Utils

@Route(path = RouterHub.TRANSLATE_DETAILACTIVITY)
class MainActivity : BaseActivity<LauncherPresenter>(), LauncherContract.View, View.OnClickListener {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerLauncherComponent.builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.launcher_main
    }

    override fun initData(savedInstanceState: Bundle?) {
        launcher_recycle.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        launcher_recycle.adapter = MainAdapter(this)

        updateLocalTime()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        registerReceiver(TimeBroadcast(), intentFilter)

        launcher_tv_nation.setOnClickListener(this)
        launcher_tv_nation_date.setOnClickListener(this)
        launcher_tv_nation_time.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        startActivity(Intent(this, CountrySelectActivity::class.java))
    }


    internal inner class TimeBroadcast : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            updateLocalTime()
        }
    }

    /**
     * Desc:更新本地时间
     *
     *
     * author: pengyushan
     * Date: 2019-09-23
     */
    private fun updateLocalTime() {
        LogUtils.debugInfo("updateLocalTime")
        launcher_tv_local_time.text = TimeUtils.getFormatTime(System.currentTimeMillis())
        launcher_tv_local_date.text = TimeUtils.getFormatDate(System.currentTimeMillis()) + " 北京"

        launcher_tv_nation_time.text = TimeUtils.getFormatTime(System.currentTimeMillis())
        launcher_tv_nation_date.text = TimeUtils.getFormatDate(System.currentTimeMillis()) + " 纽约"
    }


    override fun shonContent() {

    }

    override fun getActivity(): Activity {
        return this
    }

    override fun showMessage(message: String) {

    }

    internal inner class MainAdapter(private val mContext: Context) : RecyclerView.Adapter<MainAdapter.MainHolder>() {
        private val itemIndex: Array<String>
        private val itemImage: IntArray

        init {
            itemIndex = mContext.resources.getStringArray(R.array.launcher_item)
            itemImage = mContext.resources.getIntArray(R.array.launcher_image)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            var holder: MainHolder? = null
            val view = LayoutInflater.from(mContext).inflate(R.layout.launcher_main_item, null)
            holder = MainHolder(view)
            return holder
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            holder.title.text = itemIndex[position]
            holder.headimage.setBackgroundResource(mContext.resources.obtainTypedArray(R.array.launcher_image).getResourceId(position, 0))
            holder.itemView.setOnClickListener { view ->
                if (position == LauncherConstant.LauncherType.LAUNCHER_Setting) {
                    Utils.navigation(this@MainActivity, RouterHub.SETTING_HOME)
                } else if (position == LauncherConstant.LauncherType.LAUNCHER_TRANSLATE) {
                    Utils.navigation(this@MainActivity, RouterHub.TRANSLATE_HOME)
                } else if (position == LauncherConstant.LauncherType.LAUNCHER_HotSpot) {
                    Utils.navigation(this@MainActivity, RouterHub.HOTSPOT_HOME)
                } else if (position == LauncherConstant.LauncherType.LAUNCHER_FlowCharge) {
                    startActivity(Intent(this@MainActivity, FlowChargeActivity::class.java))
                } else if (position == LauncherConstant.LauncherType.LAUNCHER_Currency) {
                    startActivity(Intent(this@MainActivity, CurrencyConvertActivity::class.java))
                } else if (position == LauncherConstant.LauncherType.LAUNCHER_Emergency) {
                    Utils.navigation(this@MainActivity, RouterHub.LAUNCHER_EMERGENCY)
                } else if (position == LauncherConstant.LauncherType.LAUNCHER_Chat) {
                    Utils.navigation(this@MainActivity, RouterHub.CHAT_login)
                }
            }
        }

        override fun getItemCount(): Int {
            return itemIndex.size
        }

        internal inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            var headimage: ImageView
            var title: TextView

            init {
                headimage = itemView.findViewById(R.id.launcher_main_item_image)
                title = itemView.findViewById(R.id.launcher_main_item_tv)
            }
        }
    }
}
