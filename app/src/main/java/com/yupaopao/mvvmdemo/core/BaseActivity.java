package com.yupaopao.mvvmdemo.core;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

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
    protected abstract void initView(Bundle savedInstanceState);


    /**
     * 初始化viewmodel
     */
    protected void initViewModel() {
    }

    /**
     * 注册viewmodel观察
     */
    protected  void observeViewModel(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerEventBus(true);
        initViewModel();
        bindView();
        initView(savedInstanceState);
        observeViewModel();
    }

    protected void bindView() {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
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
