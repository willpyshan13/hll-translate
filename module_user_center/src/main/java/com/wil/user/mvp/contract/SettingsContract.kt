package com.wil.user.mvp.contract

import com.jess.arms.mvp.IView
import com.jess.arms.mvp.IModel


/**
 * ================================================
 * Description:
 *
 *
 * Created by MVPArmsTemplate on 08/15/2019 22:50
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * [Star me](https://github.com/JessYanCoding/MVPArms)
 * [See me](https://github.com/JessYanCoding/MVPArms/wiki)
 * [模版请保持更新](https://github.com/JessYanCoding/MVPArmsTemplate)
 * ================================================
 */
interface SettingsContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model : IModel
}
