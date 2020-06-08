package com.yupaopao.mvvmdemo.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();

    protected View layoutView;

    protected boolean needEventBus() {
        return false;
    }

    /**
     * 布局文件id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化viewmodel
     */

    protected abstract void initViewModel();

    /**
     * 注册viewmodel观察
     */
    protected abstract void observeViewModel();


    protected boolean onBackPressed() {
        if (getActivity() != null) {
            getActivity().finish();
        }
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerEventBus(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutView = inflater.inflate(getLayoutId(), container, false);
        return layoutView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);

    }

    protected void bindView(View view){
        ButterKnife.bind(this, view);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewModel();
        initView();
        observeViewModel();
    }

    @Override
    public void onDestroy() {
        registerEventBus(false);
        super.onDestroy();
    }



    private void registerEventBus(boolean register) {
        if (!needEventBus()) {
            return;
        }
        if (register && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        } else {
            EventBus.getDefault().unregister(this);
        }
    }
}
