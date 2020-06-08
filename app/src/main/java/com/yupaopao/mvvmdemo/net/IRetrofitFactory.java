package com.yupaopao.mvvmdemo.net;

import retrofit2.Retrofit;

public interface IRetrofitFactory {

    Retrofit get(String host);
    void clear();
}
