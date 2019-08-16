package me.jessyan.armscomponent.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import me.jessyan.armscomponent.mvp.contract.EmergencyContract
import me.jessyan.armscomponent.mvp.model.EmergencyModel


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
@Module
//构建EmergencyModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class EmergencyModule(private val view: EmergencyContract.View) {
    @ActivityScope
    @Provides
    fun provideEmergencyView(): EmergencyContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideEmergencyModel(model: EmergencyModel): EmergencyContract.Model {
        return model
    }
}
