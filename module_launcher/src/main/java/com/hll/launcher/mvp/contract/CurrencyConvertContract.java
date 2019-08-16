package com.hll.launcher.mvp.contract;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;


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
public interface CurrencyConvertContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

    }
}
