package me.jessyan.armscomponent.hotspot.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import me.jessyan.armscomponent.hotspot.mvp.contract.HotspotContract;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;


/**
 * Desc:
 * <p>
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
public class HotspotPresenter extends BasePresenter<HotspotContract.Model, HotspotContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public HotspotPresenter(HotspotContract.Model model, HotspotContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
