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
package me.jessyan.armscomponent.zhihu.mvp.ui.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle

import android.util.Log
import android.widget.ImageView

import com.alibaba.android.arouter.facade.annotation.Route
import com.baidu.translate.ocr.OcrCallback
import com.baidu.translate.ocr.OcrClient
import com.baidu.translate.ocr.OcrClientFactory
import com.baidu.translate.ocr.entity.Language
import com.baidu.translate.ocr.entity.OcrResult
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia

import javax.inject.Inject

import butterknife.BindView
import com.bumptech.glide.Glide
import me.jessyan.armscomponent.commonsdk.core.RouterHub
import me.jessyan.armscomponent.zhihu.R
import me.jessyan.armscomponent.zhihu.di.component.DaggerDetailComponent
import me.jessyan.armscomponent.zhihu.mvp.contract.DetailContract
import me.jessyan.armscomponent.zhihu.mvp.model.entity.ZhihuDetailBean
import me.jessyan.armscomponent.zhihu.mvp.presenter.DetailPresenter

import com.jess.arms.utils.Preconditions.checkNotNull
import me.jessyan.armscomponent.zhihu.R2.id.detail_image

/**
 * ================================================
 * 展示 View 的用法
 *
 * @see [View wiki 官方文档](https://github.com/JessYanCoding/MVPArms/wiki.2.4.2)
 * Created by JessYan on 25/04/2016 10:59
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
@Route(path = RouterHub.TRANSLATE_HOME)
class DetailActivity : BaseActivity<DetailPresenter>(), DetailContract.View {
    @Inject
    internal var mDialog: Dialog? = null

    internal var bitmap: Bitmap? = null

    internal var mImageView: ImageView? = null

    internal var client: OcrClient? = null

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this)

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.translate_activity_detail
    }

    override fun initData(savedInstanceState: Bundle?) {
        loadTitle()
        mImageView = findViewById(R.id.detail_image)
        client = OcrClientFactory.create(this, getString(R.string.translate_baidu_appid), getString(R.string.translate_baidu_appkey))
        PictureSelector.create(this@DetailActivity)
                .openCamera(PictureMimeType.ofImage())
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    // 图片、视频、音频选择结果回调
                    val selectList = PictureSelector.obtainMultipleResult(data)
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if (selectList.size > 0) {
                        bitmap = BitmapFactory.decodeFile(selectList[0].compressPath)
                        Glide.with(this).load(bitmap).into(mImageView!!)
                        if (bitmap != null) {
                            client!!.getOcrResult(Language.EN, Language.ZH, bitmap, fun(ocrResult: OcrResult) {
                                Log.d("pengyushan--", "onOcrResult(DetailActivity.java:137)-->>" + ocrResult.sumDst)
                            })
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        client = null
    }

    override fun showLoading() {
        mDialog!!.show()
    }

    override fun hideLoading() {
        mDialog!!.dismiss()
    }

    override fun showMessage(message: String) {
        checkNotNull(message)
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        checkNotNull(intent)
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    private fun loadTitle() {
        //        String title = getIntent().getStringExtra(ZhihuConstants.DETAIL_TITLE);

        title = ""
    }

    override fun shonContent(bean: ZhihuDetailBean) {}

    override fun getActivity(): Activity {
        return this
    }
}
