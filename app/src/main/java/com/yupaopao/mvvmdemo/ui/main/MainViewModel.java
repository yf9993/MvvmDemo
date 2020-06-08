package com.yupaopao.mvvmdemo.ui.main;

import com.yupaopao.mvvmdemo.api.MainApi;
import com.yupaopao.mvvmdemo.bean.UserBean;
import com.yupaopao.mvvmdemo.core.BaseSingeViewModel;

import io.reactivex.observers.DisposableObserver;

public class MainViewModel extends BaseSingeViewModel<UserBean> {
    @Override
    protected void request() {
        register( MainApi.getUserDetailTimeLineList().subscribeWith(new DisposableObserver<UserBean>() {
            @Override
            public void onNext(UserBean o) {
                onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                onFailed(new UserBean());
            }

            @Override
            public void onComplete() {

            }
        }));

    }
    // TODO: Implement the ViewModel
}