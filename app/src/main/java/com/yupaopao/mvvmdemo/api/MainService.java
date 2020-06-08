package com.yupaopao.mvvmdemo.api;

import com.yupaopao.mvvmdemo.bean.UserBean;
import com.yupaopao.mvvmdemo.bean.UserRequest;
import com.yupaopao.mvvmdemo.net.Host;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * api声明类
 */
@Host("https://api.hibixin.com")
public interface MainService {

    /**
     * 个人中心动态列表
     */
    @POST("/content/v2/timeline/list")
    Observable<UserBean> getUserDetailTimeLineList(@Body UserRequest body);
}
