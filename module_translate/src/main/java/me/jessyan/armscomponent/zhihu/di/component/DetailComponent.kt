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
package me.jessyan.armscomponent.zhihu.di.component

import com.jess.arms.di.component.AppComponent
import com.jess.arms.di.scope.ActivityScope

import dagger.BindsInstance
import dagger.Component
import me.jessyan.armscomponent.zhihu.di.module.DetailModule
import me.jessyan.armscomponent.zhihu.mvp.contract.DetailContract
import me.jessyan.armscomponent.zhihu.mvp.ui.activity.DetailActivity

/**
 * ================================================
 * 展示 Component 的用法
 *
 * @author will
 * @see [Component wiki 官方文档](https://github.com/JessYanCoding/MVPArms/wiki.2.4.6)
 * Created by JessYan on 25/04/2018 11:17
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
@ActivityScope
@Component(modules = [DetailModule::class], dependencies = [AppComponent::class])
interface DetailComponent {
    fun inject(activity: DetailActivity)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun view(view: DetailContract.View): DetailComponent.Builder

        fun appComponent(appComponent: AppComponent): DetailComponent.Builder
        fun build(): DetailComponent
    }
}