package com.wil.user.mvp.presenter

import android.app.Application

import com.jess.arms.integration.AppManager
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader

import me.jessyan.rxerrorhandler.core.RxErrorHandler

import javax.inject.Inject

import com.wil.user.mvp.contract.SettingsContract

@ActivityScope
class SettingsPresenter
@Inject
constructor(model: SettingsContract.Model, rootView: SettingsContract.View) : BasePresenter<SettingsContract.Model, SettingsContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    override fun onDestroy() {
        super.onDestroy()
    }
}
