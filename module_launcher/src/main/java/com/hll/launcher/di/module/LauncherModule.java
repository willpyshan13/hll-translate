/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hll.launcher.di.module;

import android.app.Dialog;

import com.hll.launcher.mvp.contract.LauncherContract;
import com.hll.launcher.mvp.model.LauncherModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.jessyan.armscomponent.commonres.dialog.ProgresDialog;

@Module
public abstract class LauncherModule {
    @Binds
    abstract LauncherContract.Model bindLauncherModel(LauncherModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(LauncherContract.View view){
        return new ProgresDialog(view.getActivity());
    }
}