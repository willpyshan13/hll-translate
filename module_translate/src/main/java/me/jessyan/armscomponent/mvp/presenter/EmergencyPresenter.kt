package me.jessyan.armscomponent.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject

import me.jessyan.armscomponent.mvp.contract.EmergencyContract


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
@ActivityScope
class EmergencyPresenter
@Inject
constructor(model: EmergencyContract.Model, rootView: EmergencyContract.View) :
        BasePresenter<EmergencyContract.Model, EmergencyContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager


    override fun onDestroy() {
        super.onDestroy();
    }
}
