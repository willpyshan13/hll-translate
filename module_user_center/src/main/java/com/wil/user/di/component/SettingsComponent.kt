package com.wil.user.di.component

import dagger.BindsInstance
import dagger.Component

import com.jess.arms.di.component.AppComponent

import com.wil.user.di.module.SettingsModule
import com.wil.user.mvp.contract.SettingsContract

import com.jess.arms.di.scope.ActivityScope
import com.wil.user.mvp.ui.activity.SettingsActivity


/**
 * ================================================
 * Description:
 *
 *
 * Created by MVPArmsTemplate on 08/15/2019 22:50
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * [Star me](https://github.com/JessYanCoding/MVPArms)
 * [See me](https://github.com/JessYanCoding/MVPArms/wiki)
 * [模版请保持更新](https://github.com/JessYanCoding/MVPArmsTemplate)
 * ================================================
 */
@ActivityScope
@Component(modules = [SettingsModule::class], dependencies = [AppComponent::class])
interface SettingsComponent {
    fun inject(activity: SettingsActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: SettingsContract.View): SettingsComponent.Builder

        fun appComponent(appComponent: AppComponent): SettingsComponent.Builder

        fun build(): SettingsComponent
    }
}