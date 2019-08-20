package com.hll.launcher.mvp.view;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hll.launcher.R;
import com.hll.launcher.R2;
import com.hll.launcher.constant.LauncherConstant;
import com.hll.launcher.di.component.DaggerLauncherComponent;
import com.hll.launcher.mvp.contract.LauncherContract;
import com.hll.launcher.mvp.presenter.LauncherPresenter;
import com.hll.launcher.mvp.ui.activity.CurrencyConvertActivity;
import com.hll.launcher.mvp.ui.activity.FlowChargeActivity;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;
import me.jessyan.armscomponent.commonsdk.utils.Utils;

@Route(path = RouterHub.TRANSLATE_DETAILACTIVITY)
public class MainActivity extends BaseActivity<LauncherPresenter> implements LauncherContract.View {

    @BindView(R2.id.launcher_tv_local)
    TextView mTvLocal;
    @BindView(R2.id.launcher_tv_local_time)
    TextView mTvLocalimeTime;
    @BindView(R2.id.launcher_tv_local_date)
    TextView mTvLocalDate;
    @BindView(R2.id.launcher_tv_nation)
    TextView mTvNation;
    @BindView(R2.id.launcher_tv_nation_time)
    TextView mTvNationTime;
    @BindView(R2.id.launcher_tv_nation_date)
    TextView mTvNationDate;
    @BindView(R2.id.launcher_recycle)
    RecyclerView mMainRecycle;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLauncherComponent.builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.launcher_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mMainRecycle.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
        mMainRecycle.setAdapter(new MainAdapter(this));
    }

    @Override
    public void shonContent() {

    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

        private Context mContext;
        private String[] itemIndex;
        private int[] itemImage;

        public MainAdapter(Context mContext) {
            this.mContext = mContext;
            itemIndex = mContext.getResources().getStringArray(R.array.launcher_item);
            itemImage = mContext.getResources().getIntArray(R.array.launcher_image);
        }

        @NonNull
        @Override
        public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MainHolder holder = null;
            View view = LayoutInflater.from(mContext).inflate(R.layout.launcher_main_item, null);
            holder = new MainHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MainHolder holder, int position) {
            holder.title.setText(itemIndex[position]);
            holder.headimage.setBackgroundResource(itemImage[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == LauncherConstant.LauncherType.LAUNCHER_Setting){
                        Utils.navigation(MainActivity.this, RouterHub.SETTING_HOME);
                    }else if(position ==LauncherConstant.LauncherType.LAUNCHER_TRANSLATE){
                        Utils.navigation(MainActivity.this, RouterHub.TRANSLATE_HOME);
                    }else if(position==LauncherConstant.LauncherType.LAUNCHER_HotSpot){
                        Utils.navigation(MainActivity.this, RouterHub.HOTSPOT_HOME);
                    }else if(position==LauncherConstant.LauncherType.LAUNCHER_FlowCharge){
                        startActivity(new Intent(MainActivity.this, FlowChargeActivity.class));
                    }else if(position==LauncherConstant.LauncherType.LAUNCHER_Currency){
                        startActivity(new Intent(MainActivity.this, CurrencyConvertActivity.class));
                    }else if(position==LauncherConstant.LauncherType.LAUNCHER_Emergency){
                        Utils.navigation(MainActivity.this, RouterHub.LAUNCHER_EMERGENCY);
                    }else if(position==LauncherConstant.LauncherType.LAUNCHER_Chat){
                        Utils.navigation(MainActivity.this, RouterHub.CHAT_login);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return itemIndex.length;
        }

        class MainHolder extends RecyclerView.ViewHolder {

            public ImageView headimage;
            public TextView title;

            public MainHolder(@NonNull View itemView) {
                super(itemView);
                headimage = itemView.findViewById(R.id.launcher_main_item_image);
                title = itemView.findViewById(R.id.launcher_main_item_tv);
            }
        }
    }
}
