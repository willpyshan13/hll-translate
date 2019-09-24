package com.hll.launcher.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope
import javax.inject.Inject

import com.hll.launcher.mvp.contract.CountrySelectContract


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
class CountrySelectModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), CountrySelectContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun onDestroy() {
        super.onDestroy()
    }
}
