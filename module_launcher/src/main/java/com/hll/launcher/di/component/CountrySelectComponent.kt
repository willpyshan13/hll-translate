package com.hll.launcher.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import com.hll.launcher.di.module.CountrySelectModule

import com.jess.arms.di.scope.ActivityScope
import com.hll.launcher.mvp.ui.activity.CountrySelectActivity


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
@ActivityScope
@Component(modules = arrayOf(CountrySelectModule::class), dependencies = arrayOf(AppComponent::class))
interface CountrySelectComponent {
    fun inject(activity: CountrySelectActivity)
}
