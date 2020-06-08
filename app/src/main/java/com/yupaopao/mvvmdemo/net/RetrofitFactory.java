package com.yupaopao.mvvmdemo.net;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory implements IRetrofitFactory {
    private static Map<String, Retrofit> retrofits = new HashMap<>();


    @Override
    public Retrofit get(String host) {

        if (!retrofits.containsKey(host)) {
            retrofits.put(host, create(host));
        }
        return retrofits.get(host);
    }

    @Override
    public void clear() {
        retrofits.clear();
    }

    private Retrofit create(String host) {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder.build();
    }

    @SuppressWarnings("unchecked")
    private OkHttpClient getOkHttpClient() {
        try {


            OkHttpClient.Builder builder = new OkHttpClient.Builder();



            builder = builder.connectTimeout(15000, TimeUnit.MILLISECONDS)
                    .readTimeout(15000, TimeUnit.MILLISECONDS)
                    .writeTimeout(15000, TimeUnit.MILLISECONDS);


            return builder.build();
        } catch (Exception e) {

            return new OkHttpClient.Builder()
                    .connectTimeout(15000, TimeUnit.MILLISECONDS)
                    .readTimeout(15000, TimeUnit.MILLISECONDS)
                    .writeTimeout(15000, TimeUnit.MILLISECONDS)

                    .build();
        }
    }


}
