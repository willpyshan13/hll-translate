package me.jessyan.armscomponent.hotspot.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText

import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.luck.picture.lib.tools.ToastManage
import me.jessyan.armscomponent.hotspot.R
import androidx.appcompat.app.AlertDialog
import me.jessyan.armscomponent.commonsdk.core.RouterHub
import me.jessyan.armscomponent.hotspot.constant.HotSpotConstant
import me.jessyan.armscomponent.hotspot.di.component.DaggerHotspotActivityComponent
import me.jessyan.armscomponent.hotspot.mvp.contract.HotspotContract
import me.jessyan.armscomponent.hotspot.mvp.presenter.HotspotPresenter

import com.jess.arms.utils.Preconditions.checkNotNull
import kotlinx.android.synthetic.main.activity_hotspot.*
import me.jessyan.armscomponent.hotspot.utils.WifiUtils


/**
 * Desc:个人热点页面
 *
 *
 * Date: 2019-08-16
 * Copyright: Copyright (c) 2010-2019
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [zhuanghongzhan]
 */
@Route(path = RouterHub.HOTSPOT_HOME)
class HotspotActivity : BaseActivity<HotspotPresenter>(), HotspotContract.View, View.OnClickListener {

    var wifiUtils: WifiUtils? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerHotspotActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_hotspot
    }

    override fun initData(savedInstanceState: Bundle?) {
//        wifiUtils= WifiUtils(applicationContext)
//        hotspot_item_hotspot_sw.isChecked = wifiUtils!!.isWifiApEnabled
//
//        hotspot_item_hotspot_sw.setOnCheckedChangeListener { _, isCheck ->
//            if(isCheck){
//                wifiUtils!!.openAp("zhuanghzTest","12345678")
//            }else{
//                wifiUtils!!.closeAp("zhuanghzTest","12345678")
//            }
//        }
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

    override fun onClick(view: View) {
        if (view.id == R.id.hotspot_iv_back) {
            onBackPressed()
        } else if (view.id == R.id.hotspot_item_name_tv_info) {
            showInputDialog(HotSpotConstant.DIALOG_INPUT_NAME)
        } else if (view.id == R.id.hotspot_item_pwd_tv_info) {
            showInputDialog(HotSpotConstant.DIALOG_INPUT_PWD)
        }
    }


    /**
     * Desc: 显示输入对话框，
     *
     *
     * Author: [zhuanghongzhan]
     * Date: 2019-08-16
     *
     * @param type 类型,有热点名称类型和热点密码类型
     */
    fun showInputDialog(type: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (type == HotSpotConstant.DIALOG_INPUT_NAME) "请输入热点名称" else "请输入热点密码")
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_input_layout, null)
        builder.setView(view)
        val editText = view.findViewById<EditText>(R.id.input_et)
        editText.inputType = if (type == HotSpotConstant.DIALOG_INPUT_NAME) InputType.TYPE_CLASS_TEXT else InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
        builder.setPositiveButton("确定", null)
        val alertDialog = builder.create()
        alertDialog.setOnShowListener {
            val positionButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positionButton.setOnClickListener {
                if (type == HotSpotConstant.DIALOG_INPUT_NAME) {
                    hotspot_item_name_tv_info.text = editText.text
                    alertDialog.dismiss()
                    alertDialog.setOnShowListener(null)
                } else if (type == HotSpotConstant.DIALOG_INPUT_PWD) {
                    if (editText.text.toString().length < 8) {
                        ToastManage.s(application, "密码长度不能小于8位")
                    } else {
                        hotspot_item_pwd_tv_info.text = editText.text
                        alertDialog.dismiss()
                        alertDialog.setOnShowListener(null)
                    }
                }
            }
        }
        alertDialog.show()
    }

}
