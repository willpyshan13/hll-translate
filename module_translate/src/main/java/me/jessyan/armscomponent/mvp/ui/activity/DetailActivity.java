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
package me.jessyan.armscomponent.mvp.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.translate.ocr.OcrCallback;
import com.baidu.translate.ocr.OcrClient;
import com.baidu.translate.ocr.OcrClientFactory;
import com.baidu.translate.ocr.entity.Language;
import com.baidu.translate.ocr.entity.OcrContent;
import com.baidu.translate.ocr.entity.OcrResult;
import com.bumptech.glide.Glide;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.armscomponent.R;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import me.jessyan.armscomponent.di.component.DaggerDetailComponent;
import me.jessyan.armscomponent.widget.TextImageView;
import me.jessyan.armscomponent.mvp.contract.DetailContract;
import me.jessyan.armscomponent.mvp.model.entity.ZhihuDetailBean;
import me.jessyan.armscomponent.mvp.presenter.DetailPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================
 * 展示 View 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.2">View wiki 官方文档</a>
 * Created by JessYan on 25/04/2016 10:59
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@Route(path = RouterHub.TRANSLATE_HOME)
public class DetailActivity extends BaseActivity<DetailPresenter> implements DetailContract.View {
    @Inject
    Dialog mDialog;

    Bitmap bitmap;

    private TextImageView mImageView;

    OcrClient client;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        ArmsUtils.statuInScreen(this);
        return R.layout.translate_activity_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mImageView = findViewById(R.id.detail_image);
        loadTitle();
        client = OcrClientFactory.create(this, getString(R.string.translate_baidu_appid), getString(R.string.translate_baidu_appkey));
        PictureSelector.create(DetailActivity.this)
                .openCamera(PictureMimeType.ofImage())
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if (selectList.size() > 0) {
                        bitmap = BitmapFactory.decodeFile(selectList.get(0).getCompressPath()).copy(Bitmap.Config.ARGB_8888, true);
//                        Glide.with(this).load(bitmap).into(mImageView);
                        if (bitmap != null) {
                            showLoading();
                            client.getOcrResult(Language.EN, Language.ZH, bitmap, new OcrCallback() {
                                @Override
                                public void onOcrResult(OcrResult ocrResult) {
                                    Log.d("pengyushan--", "onOcrResult(DetailActivity.java:137)-->>" + ocrResult.getErrorMsg());
                                    drawableContent(ocrResult.getContents());
                                }
                            });
                        }
                    }else {
                        finish();
                    }

                    break;
            }
        }else {
            finish();
        }
    }

    public void drawableContent(List data) {
        if (data != null && data.size() > 0) {
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setTextSize(35);
            paint.setColor(Color.YELLOW);
            for (int i = 0; i < data.size(); i++) {
                int centerX = ((OcrContent) data.get(i)).getRect().centerX() - ((OcrContent) data.get(i)).getRect().width() / 2;
                int centerY = ((OcrContent) data.get(i)).getRect().centerY();
                Log.d("draw", "centerX==" + centerX + " centerY== " + centerY + "  " + ((OcrContent) data.get(i)).getDst());
                canvas.drawText(((OcrContent) data.get(i)).getDst(), centerX, centerY, paint);
            }
            canvas.save();
            canvas.restore();
            hideLoading();
            Glide.with(this).load(bitmap).into(mImageView);
        }
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    private void loadTitle() {
//        String title = getIntent().getStringExtra(ZhihuConstants.DETAIL_TITLE);

        setTitle("hello");
    }

    @Override
    public void shonContent(ZhihuDetailBean bean) {
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
