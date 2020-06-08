package com.yupaopao.mvvmdemo.net;

import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Retrofit;

public class ApiServiceManager {
    private static final String TAG = ApiServiceManager.class.getSimpleName();

    private static volatile ApiServiceManager mApiServiceManager = null;
    private final Map<String, Object> mServiceMap = new ConcurrentHashMap<>();
    private static IRetrofitFactory mRetrofitFactory;

    public static ApiServiceManager getInstance() {
        if (mApiServiceManager == null) {
            synchronized (ApiServiceManager.class) {
                if (mApiServiceManager == null) {
                    mApiServiceManager = new ApiServiceManager();
                }
            }
        }
        return mApiServiceManager;
    }

    private ApiServiceManager() {
        mRetrofitFactory = new RetrofitFactory();
    }


    @SuppressWarnings("unchecked")
    public <T> T obtainService(Class<T> service) {
        if(mServiceMap.containsKey(service.getName())) {
            return (T) mServiceMap.get(service.getName());
        }
        try {
            Host host = service.getAnnotation(Host.class);

            String hostUrl = host.value();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return (T) mServiceMap.get(service.getName());
    }

    private <T> void putMap(Class<T> service, Retrofit retrofit) {
        if (retrofit != null && !mServiceMap.containsKey(service.getName())) {
            mServiceMap.put(service.getName(), retrofit.create(service));
        }
    }

    public void onDestroy() {
        mApiServiceManager = null;
        if(mRetrofitFactory != null) {
            mRetrofitFactory.clear();
        }
        mServiceMap.clear();
    }



}
