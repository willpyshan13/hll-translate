package com.wil.user.app

import android.app.Application
import android.content.Context

import com.jess.arms.base.delegate.AppLifecycles
import com.jess.arms.di.module.GlobalConfigModule
import com.jess.arms.integration.ConfigModule
import com.jess.arms.integration.cache.IntelligentCache
import com.jess.arms.utils.ArmsUtils
import com.squareup.leakcanary.RefWatcher

import com.wil.user.BuildConfig

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * ================================================
 * 组件的全局配置信息在此配置, 需要将此实现类声明到 AndroidManifest 中
 * CommonSDK 中已有 GlobalConfiguration 配置有所有组件都可公用的配置信息
 * 这里用来配置一些组件自身私有的配置信息
 *
 * @see com.jess.arms.base.delegate.AppDelegate
 *
 * @see com.jess.arms.integration.ManifestParser
 *
 * @see [ConfigModule wiki 官方文档](https://github.com/JessYanCoding/ArmsComponent/wiki.3.3)
 * Created by ArmsComponentTemplate on 08/15/2019 22:45
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * [Star me](https://github.com/JessYanCoding/ArmsComponent)
 * [See me](https://github.com/JessYanCoding/ArmsComponent/wiki)
 * [模版请保持更新](https://github.com/JessYanCoding/ArmsComponent-Template)
 * ================================================
 */
class GlobalConfiguration : ConfigModule {

    override fun applyOptions(context: Context, builder: GlobalConfigModule.Builder) {

    }

    override fun injectAppLifecycle(context: Context, lifecycles: MutableList<AppLifecycles>) {
        // AppLifecycles 的所有方法都会在基类 Application 的对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        // 可以根据不同的逻辑添加多个实现类
        lifecycles.add(AppLifecyclesImpl())
    }

    override fun injectActivityLifecycle(context: Context, lifecycles: List<Application.ActivityLifecycleCallbacks>) {

    }

    override fun injectFragmentLifecycle(context: Context, lifecycles: MutableList<FragmentManager.FragmentLifecycleCallbacks>) {
        //当所有模块集成到宿主 App 时, 在 App 中已经执行了以下代码, 所以不需要再执行
        if (BuildConfig.IS_BUILD_MODULE) {
            lifecycles.add(object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                    (ArmsUtils
                            .obtainAppComponentFromContext(f.activity)
                            .extras()
                            .get(IntelligentCache.getKeyOfKeep(RefWatcher::class.java.name)) as RefWatcher)
                            .watch(f)
                }
            })
        }
    }
}
