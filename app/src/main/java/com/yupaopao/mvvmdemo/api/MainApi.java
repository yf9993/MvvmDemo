package com.yupaopao.mvvmdemo.api;

import com.yupaopao.mvvmdemo.bean.UserBean;
import com.yupaopao.mvvmdemo.bean.UserRequest;
import com.yupaopao.mvvmdemo.net.ApiServiceManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 请求api实现类
 */
public class MainApi {

    /**
     * 个人中心动态列表
     */
    public static Observable<UserBean> getUserDetailTimeLineList() {
        UserRequest userRequest=new UserRequest();
        userRequest.setId(2);
        return ApiServiceManager.getInstance().obtainService(MainService.class)
                .getUserDetailTimeLineList(userRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
