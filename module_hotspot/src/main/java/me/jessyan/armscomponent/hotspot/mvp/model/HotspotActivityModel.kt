package me.jessyan.armscomponent.hotspot.mvp.model

import android.app.Application

import com.google.gson.Gson
import com.jess.arms.integration.IRepositoryManager
import com.jess.arms.mvp.BaseModel

import com.jess.arms.di.scope.ActivityScope

import javax.inject.Inject

import me.jessyan.armscomponent.hotspot.mvp.contract.HotspotContract


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
class HotspotActivityModel @Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), HotspotContract.Model {
    @Inject
    @JvmField
    internal var mGson: Gson? = null
    @Inject
    @JvmField
    internal var mApplication: Application? = null

    override fun onDestroy() {
        super.onDestroy()
        this.mGson = null
        this.mApplication = null
    }
}