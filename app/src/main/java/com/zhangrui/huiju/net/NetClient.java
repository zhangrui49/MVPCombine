package com.zhangrui.huiju.net;

import com.zhangrui.huiju.global.App;
import com.zhangrui.huiju.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */
public class NetClient {

    private volatile static NetClient mInstance = new NetClient();

    private static Retrofit sAiPaiRetrofit;
    private static Retrofit sGankRetrofit;
    private static Retrofit sZhihuRetrofit;
    private static Retrofit sDoubanRetrofit;
    private OkHttpClient sOkHttpClient;

    public static NetClient getInstance() {
        if (mInstance == null) {
            synchronized (NetClient.class) {
                if (mInstance == null) {
                    mInstance = new NetClient();
                }
            }
        }
        return mInstance;
    }

    private NetClient() {
    }

    /**
     * 美拍
     */
    public Retrofit getVideoRetrofit() {

        if (sAiPaiRetrofit == null) {
            sAiPaiRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.AiPaiApi.AIPAI_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return sAiPaiRetrofit;

    }

    /**
     * Gank
     */
    public Retrofit getGankRetrofit() {

        if (sGankRetrofit == null) {
            sGankRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.GankApi.GANK_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return sGankRetrofit;
    }

    /**
     * Zhihu
     */
    public Retrofit getZhihuRetrofit() {

        if (sZhihuRetrofit == null) {
            sZhihuRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.ZhihuApi.ZHIHU_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return sZhihuRetrofit;
    }

    /**
     * 豆瓣
     */
    public Retrofit getDoubanRetrofit() {

        if (sDoubanRetrofit == null) {
            sDoubanRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.DouBanApi.DOUBAN_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return sDoubanRetrofit;
    }


    /**
     * 初始化okhttp
     */
    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            //cache url
            File httpCacheDirectory = new File(App.getInstance().getExternalCacheDir(), "responses");
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(httpCacheDirectory, cacheSize);
            builder.cache(cache);
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            sOkHttpClient = builder.build();
        }
        return sOkHttpClient;
    }

    private static Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365, TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();
            Request request = chain.request();
            if (!Utils.isNetworkAvailable(App.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();

            }
            Response originalResponse = chain.proceed(request);
            if (Utils.isNetworkAvailable(App.getInstance())) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-xcached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

}
