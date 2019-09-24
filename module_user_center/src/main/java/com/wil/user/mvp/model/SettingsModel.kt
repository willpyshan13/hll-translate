package com.wil.user.mvp.model

import android.app.Application

import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope

import javax.inject.Inject

import com.wil.user.mvp.contract.SettingsContract


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
class SettingsModel @Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), SettingsContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun onDestroy() {
        super.onDestroy()
    }
}