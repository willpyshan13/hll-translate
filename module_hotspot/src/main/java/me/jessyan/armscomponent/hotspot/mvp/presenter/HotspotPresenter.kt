package me.jessyan.armscomponent.hotspot.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader

import me.jessyan.armscomponent.hotspot.mvp.contract.HotspotContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler

import javax.inject.Inject


/**
 * Desc:
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
@ActivityScope
class HotspotPresenter @Inject
constructor(model: HotspotContract.Model, rootView: HotspotContract.View) : BasePresenter<HotspotContract.Model, HotspotContract.View>(model, rootView) {
    @Inject
    @JvmField
    internal var mErrorHandler: RxErrorHandler? = null
    @Inject
    @JvmField
    internal var mApplication: Application? = null
    @Inject
    @JvmField
    internal var mImageLoader: ImageLoader? = null
    @Inject
    @JvmField
    internal var mAppManager: AppManager? = null

    override fun onDestroy() {
        super.onDestroy()
        this.mErrorHandler = null
        this.mAppManager = null
        this.mImageLoader = null
        this.mApplication = null
    }
}
