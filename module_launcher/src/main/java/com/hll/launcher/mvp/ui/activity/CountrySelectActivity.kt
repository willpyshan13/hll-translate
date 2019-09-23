package com.hll.launcher.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.hll.launcher.di.component.DaggerCountrySelectComponent
import com.hll.launcher.di.module.CountrySelectModule
import com.hll.launcher.mvp.contract.CountrySelectContract
import com.hll.launcher.mvp.presenter.CountrySelectPresenter

import com.hll.launcher.R
import kotlinx.android.synthetic.main.launcher_country_item.view.*
import kotlinx.android.synthetic.main.launcher_country_select.*


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
        return R.layout.launcher_country_select
    }


    override fun initData(savedInstanceState: Bundle?) {
        launcher_country_recycle.layoutManager = LinearLayoutManager(this)
        launcher_country_recycle.adapter = RecycleAdapter()

    }

    class RecycleAdapter :RecyclerView.Adapter<RecycleViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.launcher_country_item,null)
            return RecycleViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 5
        }

        override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
            holder.textView.launcher_country_item_tv.text ="111111"
        }

    }

    class RecycleViewHolder(val textView: View) : RecyclerView.ViewHolder(textView)

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
