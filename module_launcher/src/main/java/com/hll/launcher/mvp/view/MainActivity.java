package com.hll.launcher.mvp.view;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hll.launcher.R;
import com.hll.launcher.R2;
import com.hll.launcher.di.component.DaggerLauncherComponent;
import com.hll.launcher.mvp.contract.LauncherContract;
import com.hll.launcher.mvp.presenter.LauncherPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import javax.inject.Inject;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import me.jessyan.armscomponent.commonsdk.core.RouterHub;

@Route(path = RouterHub.ZHIHU_DETAILACTIVITY)
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
        mMainRecycle.setLayoutManager(new GridLayoutManager(this,2, GridLayoutManager.HORIZONTAL,false));
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

    class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder>{

        private Context mContext;

        public MainAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(mContext).inflate(R.layout.launcher_main_item,null);

            return new MainHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MainHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 8;
        }

        class MainHolder extends RecyclerView.ViewHolder{

            @BindView(R2.id.launcher_main_item_image)
            public ImageView headimage;
            @BindView(R2.id.launcher_main_item_tv)
            public TextView title;

            public MainHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
