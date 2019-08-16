package me.jessyan.armscomponent.hotspot.di.component

import dagger.BindsInstance
import dagger.Component

import com.jess.arms.di.component.AppComponent

import me.jessyan.armscomponent.hotspot.di.module.HotspotActivityModule
import me.jessyan.armscomponent.hotspot.mvp.contract.HotspotContract

import com.jess.arms.di.scope.ActivityScope

import me.jessyan.armscomponent.hotspot.mvp.ui.activity.HotspotActivity


/**
 * ================================================
 * Description:
 *
 *
 * Created by MVPArmsTemplate on 08/16/2019 09:17
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * [Star me](https://github.com/JessYanCoding/MVPArms)
 * [See me](https://github.com/JessYanCoding/MVPArms/wiki)
 * [模版请保持更新](https://github.com/JessYanCoding/MVPArmsTemplate)
 * ================================================
 */
@ActivityScope
@Component(modules = [HotspotActivityModule::class], dependencies = [AppComponent::class])
interface HotspotActivityComponent {
    fun inject(activity: HotspotActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: HotspotContract.View): HotspotActivityComponent.Builder

        fun appComponent(appComponent: AppComponent): HotspotActivityComponent.Builder

        fun build(): HotspotActivityComponent
    }
}