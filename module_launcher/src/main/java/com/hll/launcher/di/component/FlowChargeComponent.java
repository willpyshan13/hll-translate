package com.hll.launcher.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.hll.launcher.di.module.FlowChargeModule;
import com.hll.launcher.mvp.contract.FlowChargeContract;

import com.jess.arms.di.scope.ActivityScope;
import com.hll.launcher.mvp.ui.activity.FlowChargeActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 08/16/2019 11:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@ActivityScope
@Component(modules = FlowChargeModule.class, dependencies = AppComponent.class)
public interface FlowChargeComponent {
    void inject(FlowChargeActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        FlowChargeComponent.Builder view(FlowChargeContract.View view);

        FlowChargeComponent.Builder appComponent(AppComponent appComponent);

        FlowChargeComponent build();
    }
}