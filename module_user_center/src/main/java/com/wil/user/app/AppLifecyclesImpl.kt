package com.wil.user.app

import android.app.Application
import android.content.Context

import com.jess.arms.base.delegate.AppLifecycles
import com.jess.arms.integration.cache.IntelligentCache
import com.jess.arms.utils.ArmsUtils
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

import com.wil.user.BuildConfig

/**
 * ================================================
 * 展示 [AppLifecycles] 的用法
 *
 *
 * Created by ArmsComponentTemplate on 08/15/2019 22:45
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * [Star me](https://github.com/JessYanCoding/ArmsComponent)
 * [See me](https://github.com/JessYanCoding/ArmsComponent/wiki)
 * [模版请保持更新](https://github.com/JessYanCoding/ArmsComponent-Template)
 * ================================================
 */
class AppLifecyclesImpl : AppLifecycles {

    override fun attachBaseContext(base: Context) {

    }

    override fun onCreate(application: Application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        //当所有模块集成到宿主 App 时, 在 App 中已经执行了以下代码
        if (BuildConfig.IS_BUILD_MODULE) {
            //leakCanary内存泄露检查
            ArmsUtils.obtainAppComponentFromContext(application).extras()
                    .put(IntelligentCache.getKeyOfKeep(RefWatcher::class.java.name), if (BuildConfig.USE_CANARY) LeakCanary.install(application) else RefWatcher.DISABLED)
        }
    }

    override fun onTerminate(application: Application) {

    }
}
