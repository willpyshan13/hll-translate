package com.hll.launcher.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.hll.launcher.di.module.CurrencyConvertModule;
import com.hll.launcher.mvp.contract.CurrencyConvertContract;

import com.jess.arms.di.scope.ActivityScope;
import com.hll.launcher.mvp.ui.activity.CurrencyConvertActivity;


/**
 * Desc:
 * <p>
 * Created by will on 08/16/2019 11:26
 * Copyright: Copyright (c) 2018-2019
 * Company:
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [pengyushan]
 */
@ActivityScope
@Component(modules = CurrencyConvertModule.class, dependencies = AppComponent.class)
public interface CurrencyConvertComponent {
    void inject(CurrencyConvertActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CurrencyConvertComponent.Builder view(CurrencyConvertContract.View view);

        CurrencyConvertComponent.Builder appComponent(AppComponent appComponent);

        CurrencyConvertComponent build();
    }
}