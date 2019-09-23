package com.hll.launcher.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import com.hll.launcher.mvp.contract.CountrySelectContract
import com.hll.launcher.mvp.model.CountrySelectModel


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
@Module
//构建CountrySelectModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class CountrySelectModule(private val view: CountrySelectContract.View) {
    @ActivityScope
    @Provides
    fun provideCountrySelectView(): CountrySelectContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideCountrySelectModel(model: CountrySelectModel): CountrySelectContract.Model {
        return model
    }
}
