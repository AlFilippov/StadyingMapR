package com.alphilippov.studyingmap.network;

import com.alphilippov.studyingmap.network.dto.UserModelDto;
import com.alphilippov.studyingmap.utils.AppConfig;
import com.google.common.io.BaseEncoding;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
/**
Подготовка к реализации в presenter
 */
public class UdemyApi implements UdemyApiIn {
    private OkHttpClient setHttpInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", "Basic " + getBasicAuthenticator());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }).addInterceptor(httpLoggingInterceptor).build();
        return client;
    }


    private String getBasicAuthenticator() {
        String authStr = AppConfig.Authorization.CLIENT_ID + ":" + AppConfig.Authorization.CLIENT_SECRET;
        return BaseEncoding.base64().encode(authStr.getBytes());

    }

    @Override
    public Call<UserModelDto> getResult(int page, int page_size, String search, String price, boolean aff, String lang, String level, String order, int ratings) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(AppConfig.BASE_URL)
                .client(setHttpInterceptor())
                .build();
        UdemyApiService udemyApiService = retrofit.create(UdemyApiService.class);
        return udemyApiService.getResult(page, page_size, search, price, aff, lang, level, order, ratings);

    }
}
