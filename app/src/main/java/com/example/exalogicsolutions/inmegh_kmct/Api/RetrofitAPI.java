package com.example.exalogicsolutions.inmegh_kmct.Api;

import android.content.Context;

import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAPI {

    private static RetrofitAPI _me;
    private static RestAdapter restAdapter;
    private static Context mContext;

    private RetrofitAPI() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);


        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constants.Host)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient))
                .setRequestInterceptor((RequestInterceptor) new MyRequestInterceptor(mContext))
                .build();
    }


    public static RetrofitAPIInterface getApi() {
        if (_me == null) {
            getInstance(mContext);
        }
        return restAdapter.create(RetrofitAPIInterface.class);
    }

    public static RetrofitAPI getInstance(Context context) {
        mContext = context;
        if (_me == null) {
            synchronized (RetrofitAPI.class) {
                if (_me == null) {
                    _me = new RetrofitAPI();
                }
            }
        }
        return _me;
    }
    private static final String BASE_URL = "http://192.168.1.46:4000/api/v1/";

    public static Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
