package com.alphilippov.studyingmap.utils

import ApplicationSchedulers
import DefaultNetworkRepository
import android.content.Context
import com.alphilippov.studyingmap.BuildConfig
import com.alphilippov.studyingmap.data.DataApi
import com.alphilippov.studyingmap.data.DefaultRepository
import com.alphilippov.studyingmap.data.OkHttpHelper
import com.alphilippov.studyingmap.data.Repository
import com.google.common.io.BaseEncoding

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DependenciesFactory {
    fun createSchedulers() = ApplicationSchedulers()
    fun createRepository(context: Context): Repository {
        val schedulers = createSchedulers()
        val retrofit = createRetrofit(schedulers)
        val dataApi = retrofit.create(DataApi::class.java)
        val networkRepository = createNetworkRepository(dataApi, schedulers)
        return DefaultRepository(networkRepository)

    }

    fun getBasicAuthenticator(): String {
        return BaseEncoding.base64().encode((BuildConfig.END_POINT_BACKEND + ":" + BuildConfig.END_POINT_IP_JSON).toByteArray())
    }

    private fun createRetrofit(schedulers: Schedulers) =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.END_POINT_BACKEND)
                    .client(OkHttpHelper().createOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.io()))
                    .build()

    private fun createNetworkRepository(api: DataApi, schedulers: Schedulers) =
            DefaultNetworkRepository(api, schedulers)
}