package me.jessyan.armscomponent.hotspot.di.module

import dagger.Binds
import dagger.Module

import me.jessyan.armscomponent.hotspot.mvp.contract.HotspotContract
import me.jessyan.armscomponent.hotspot.mvp.model.HotspotActivityModel


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
@Module
abstract class HotspotActivityModule {

    @Binds
    internal abstract fun bindHotspotActivityModel(model: HotspotActivityModel): HotspotContract.Model
}