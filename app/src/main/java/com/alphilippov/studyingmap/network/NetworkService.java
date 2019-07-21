package com.alphilippov.studyingmap.network;

import com.alphilippov.studyingmap.utils.AppConfig;
import com.google.common.io.BaseEncoding;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class NetworkService {
//TODO:Переделать в несколько отдельных реализаций экземпляров Retrofit

    public static RestService restUdemy() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", "Basic " + getBasicAuthenticator());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }).addInterceptor(httpLoggingInterceptor).build();


        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return mRetrofit.create(RestService.class);
    }

    private static String getBasicAuthenticator() {
        String authStr = AppConfig.Authorization.CLIENT_ID + ":" + AppConfig.Authorization.CLIENT_SECRET;
        return BaseEncoding.base64().encode(authStr.getBytes());

    }


}
