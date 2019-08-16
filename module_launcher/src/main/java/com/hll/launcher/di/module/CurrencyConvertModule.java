package com.hll.launcher.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.hll.launcher.mvp.contract.CurrencyConvertContract;
import com.hll.launcher.mvp.model.CurrencyConvertModel;


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
@Module
public abstract class CurrencyConvertModule {

    @Binds
    abstract CurrencyConvertContract.Model bindCurrencyConvertModel(CurrencyConvertModel model);
}