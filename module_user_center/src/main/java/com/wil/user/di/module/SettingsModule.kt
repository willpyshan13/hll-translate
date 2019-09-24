package com.wil.user.di.module

import com.jess.arms.di.scope.ActivityScope

import dagger.Binds
import dagger.Module
import dagger.Provides

import com.wil.user.mvp.contract.SettingsContract
import com.wil.user.mvp.model.SettingsModel


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
@Module
abstract class SettingsModule {

    @Binds
    internal abstract fun bindSettingsModel(model: SettingsModel): SettingsContract.Model
}