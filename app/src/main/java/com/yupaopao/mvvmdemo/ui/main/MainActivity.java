package com.yupaopao.mvvmdemo.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.yupaopao.mvvmdemo.R;
import com.yupaopao.mvvmdemo.core.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if(savedInstanceState ==null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

}