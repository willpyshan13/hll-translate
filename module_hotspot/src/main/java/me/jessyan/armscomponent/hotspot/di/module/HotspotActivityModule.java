package me.jessyan.armscomponent.hotspot.di.module;

import dagger.Binds;
import dagger.Module;

import me.jessyan.armscomponent.hotspot.mvp.contract.HotspotContract;
import me.jessyan.armscomponent.hotspot.mvp.model.HotspotActivityModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/16/2019 09:17
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class HotspotActivityModule {

    @Binds
    abstract HotspotContract.Model bindHotspotActivityModel(HotspotActivityModel model);
}