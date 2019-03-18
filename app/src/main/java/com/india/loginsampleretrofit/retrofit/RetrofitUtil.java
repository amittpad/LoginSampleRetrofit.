package com.india.loginsampleretrofit.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Agniva on 04-10-2018.
 */
public class RetrofitUtil {

    public static RestInterface retrofit(String url) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();




        RestInterface service;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())

                .build();
        service = retrofit.create(RestInterface.class);

        return service;
    }

//    private static Retrofit retrofit = null;
//
//    public static Retrofit getService() {
//
//        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        if (BuildConfig.DEBUG) {
//            okHttpClient.addInterceptor(loggingInterceptor);
//        }
//        retrofit = new Retrofit.Builder()
//                .client(okHttpClient.build())
//                .baseUrl("http://18.191.246.227/mycitiz_beta/webservice/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//
//
//
//        // GitHubService service = retrofit.create(GitHubService.class);
//
//
//
//       /* HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://www.stage.tshirtdesignservices.com/")
//                .addConverterFactory(new NullOnEmptyConverterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();*/
//
//
//        return retrofit;
}
