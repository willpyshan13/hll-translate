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
package com.hll.launcher.di.component;

import com.hll.launcher.di.module.LauncherModule;
import com.hll.launcher.mvp.contract.LauncherContract;
import com.hll.launcher.mvp.view.MainActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;

@ActivityScope
@Component(modules = LauncherModule.class, dependencies = AppComponent.class)
public interface LauncherComponent {
    void inject(MainActivity activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        LauncherComponent.Builder view(LauncherContract.View view);
        LauncherComponent.Builder appComponent(AppComponent appComponent);
        LauncherComponent build();
    }
}