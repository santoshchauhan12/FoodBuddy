package com.droidpro.foodbuddy.network;


import android.support.annotation.NonNull;

import com.droidpro.foodbuddy.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * This class has common code to build retrofit
 */
public class RetrofitProvider {

    public static final String TAG = RetrofitProvider.class.getSimpleName();
    private static final int TIMEOUT = 20;
    /*private static final String BASE_URL_READ = "https://molekule-backend-integration.molekule.com/";
    private static final String BASE_URL_WRITE = "https://molekule-backend-integration.molekule.com/";*/
   /* private static final String BASE_URL_READ = "https://molekule-backend-staging.molekule.com/";
    private static final String BASE_URL_WRITE = "https://molekule-backend-staging.molekule.com/";*/

    private static RetrofitProvider sRetrofitInstance;
    private static final String BASE_URL_READ = "https://api-v1.molekule.com/";
    private static final String BASE_URL_WRITE = "https://api-v1.molekule.com/";
    private FoodBuddyRestApi mMolekuleReadRestApi;
    private FoodBuddyRestApi mMolekuleWriteRestApi;

    private RetrofitProvider() {
        buildReadRestApi();
    }

    public static RetrofitProvider getInstance() {
        if (sRetrofitInstance == null) {
            synchronized (RetrofitProvider.class) {
                if (sRetrofitInstance == null) {
                    sRetrofitInstance = new RetrofitProvider();
                }
            }
        }
        return sRetrofitInstance;
    }

    public FoodBuddyRestApi getReadRestApi() {
        if (null == mMolekuleReadRestApi) {
            buildReadRestApi();
        }
        return mMolekuleReadRestApi;
    }

    public FoodBuddyRestApi getWriteRestApi() {
        if (null == mMolekuleWriteRestApi) {
            buildWriteRestApi();
        }
        return mMolekuleWriteRestApi;
    }

    private void buildReadRestApi() {
        OkHttpClient.Builder okHttpClient = getOkHttpBuilder();
        okHttpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                /*if (null != originalRequest && UserSettingPrefManager.getInstance() != null) {
                    String userToken = UserSettingPrefManager.getInstance().readPreference(MolekuleConstants.PREF.USER_TOKEN);
                    Log.d(TAG, "Token" + userToken);
                    Request.Builder reqBuilder = originalRequest.newBuilder();
                    if (null != reqBuilder) {
                        reqBuilder.addHeader("token", userToken);
                        Request newReq = reqBuilder.build();
                        return chain.proceed(newReq);
                    }

                }*/
                return chain.proceed(originalRequest);
            }
        });
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL_READ)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient.build());
        mMolekuleReadRestApi = retrofitBuilder.build().create(FoodBuddyRestApi.class);
    }

    private void buildWriteRestApi() {
        OkHttpClient.Builder okHttpClient = getOkHttpBuilder();
        okHttpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                /*if (null != originalRequest && UserSettingPrefManager.getInstance() != null) {
                    String userToken = UserSettingPrefManager.getInstance().readPreference(MolekuleConstants.PREF.USER_TOKEN);
                    Logger.d(TAG, "Token" + userToken);
                    Request.Builder reqBuilder = originalRequest.newBuilder();
                    if (null != reqBuilder) {
                        reqBuilder.addHeader("token", userToken);
                        Request newReq = reqBuilder.build();
                        return chain.proceed(newReq);
                    }

                }*/
                return chain.proceed(originalRequest);
            }
        });
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL_WRITE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient.build());
        mMolekuleWriteRestApi = retrofitBuilder.build().create(FoodBuddyRestApi.class);
    }

    //Get ok http object
    @NonNull
    private OkHttpClient.Builder getOkHttpBuilder() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);
        }
        return okHttpClientBuilder;
    }
}


