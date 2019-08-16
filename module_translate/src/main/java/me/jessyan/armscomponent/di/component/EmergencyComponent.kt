package me.jessyan.armscomponent.di.component

import dagger.Component
import com.jess.arms.di.component.AppComponent

import me.jessyan.armscomponent.di.module.EmergencyModule

import com.jess.arms.di.scope.ActivityScope
import me.jessyan.armscomponent.mvp.ui.activity.EmergencyActivity


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
@Component(modules = arrayOf(EmergencyModule::class), dependencies = arrayOf(AppComponent::class))
interface EmergencyComponent {
    fun inject(activity: EmergencyActivity)
}
