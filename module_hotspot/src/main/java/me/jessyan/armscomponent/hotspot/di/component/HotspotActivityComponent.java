package me.jessyan.armscomponent.hotspot.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.armscomponent.hotspot.di.module.HotspotActivityModule;
import me.jessyan.armscomponent.hotspot.mvp.contract.HotspotContract;

import com.jess.arms.di.scope.ActivityScope;

import me.jessyan.armscomponent.hotspot.mvp.ui.activity.HotspotActivity;


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
@ActivityScope
@Component(modules = HotspotActivityModule.class, dependencies = AppComponent.class)
public interface HotspotActivityComponent {
    void inject(HotspotActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HotspotActivityComponent.Builder view(HotspotContract.View view);

        HotspotActivityComponent.Builder appComponent(AppComponent appComponent);

        HotspotActivityComponent build();
    }
}