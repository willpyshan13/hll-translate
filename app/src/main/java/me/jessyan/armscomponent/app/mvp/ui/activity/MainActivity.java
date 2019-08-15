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
package me.jessyan.armscomponent.app.mvp.ui.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.armscomponent.app.R;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import me.jessyan.armscomponent.commonsdk.utils.Utils;


/**
 * Desc:宿主主页
 * <p>
 * Date: 2019-08-15
 * Copyright: Copyright (c) 2018-2019
 * Company: @
 * Updater:
 * Update Time:
 * Update Comments:
 *
 * @Author: [pengysh]
 */
@Route(path = RouterHub.APP_MAINACTIVITY)
public class MainActivity extends BaseActivity {
    /**
     *
     */
    @BindView(R.id.bt_zhihu)
    Button mZhihuButton;
    /**
     *
     */
    @BindView(R.id.bt_gank)
    Button mGankButton;
    /**
     *
     */
    @BindView(R.id.bt_gold)
    Button mGoldButton;

    /**
     *
     */
    private long mPressedTime;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        //这里想展示组件向外提供服务的功能, 模拟下组件向宿主提供一些必要的信息, 这里为了简单就直接返回本地数据不请求网络了
    }


    @Override
    public void onBackPressed() {
        //获取第一次按键时间
        long mNowTime = System.currentTimeMillis();
        //比较两次按键时间差
        if ((mNowTime - mPressedTime) > 2000) {
            ArmsUtils.makeText(getApplicationContext(),
                    "再按一次退出" + ArmsUtils.getString(getApplicationContext(), R.string.public_app_name));
            mPressedTime = mNowTime;
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 这里注意下在组件的页面中(使用了 R2 的页面)使用 {@link butterknife.OnClick} 会有概率出现 id 不正确的问题, 使用以下方式解决
     * <pre>
     * @param view
     * @OnClick({R2.id.button1, R2.id.button2 }) public void Onclick(View view){      if (view.getId() == R.id.button1){          ...      } else if(view.getId() == R.id.button2){          ...      } } </pre> <p> 在注解上使用 R2, 下面使用 R, 并且使用 {@code if else}, 替代 {@code switch}
     */
    @OnClick({R.id.bt_zhihu, R.id.bt_gank, R.id.bt_gold})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_zhihu:
                Utils.navigation(MainActivity.this, RouterHub.TRANSLATE_DETAILACTIVITY);
                break;
            case R.id.bt_gank:
                Utils.navigation(MainActivity.this, RouterHub.TRANSLATE_DETAILACTIVITY);
                break;
            case R.id.bt_gold:
                Utils.navigation(MainActivity.this, RouterHub.TRANSLATE_DETAILACTIVITY);
                break;
        }
    }
}
