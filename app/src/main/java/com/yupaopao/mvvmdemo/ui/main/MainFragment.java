package com.yupaopao.mvvmdemo.ui.main;

import android.widget.TextView;

import com.yupaopao.mvvmdemo.R;
import com.yupaopao.mvvmdemo.bean.UserBean;
import com.yupaopao.mvvmdemo.core.BaseFragment;

import butterknife.BindView;

public class MainFragment extends BaseFragment {

    @BindView(R.id.message)
    TextView message;
    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initView() {
        message.setText("addd");
    }

    @Override
    protected void initViewModel() {
        mViewModel = getDefaultViewModelProviderFactory().create(MainViewModel.class);
    }

    @Override
    protected void observeViewModel() {
        mViewModel.observe(this,this::onUserDataChange);
        //mViewModel.request(); 请求的地方
    }

    private void onUserDataChange(UserBean userBean){
        message.setText(userBean.name);
    }


}