package com.alphilippov.studyingmap.utils

import ApplicationSchedulers
import DefaultNetworkRepository
import android.content.Context
import com.alphilippov.studyingmap.BuildConfig
import com.alphilippov.studyingmap.data.DataApi
import com.alphilippov.studyingmap.data.DefaultRepository
import com.alphilippov.studyingmap.data.OkHttpHelper
import com.alphilippov.studyingmap.data.Repository
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



    private fun createRetrofit(schedulers: Schedulers) =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.UDEMY_API)
                    .client(OkHttpHelper().createOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.io()))
                    .build()

    private fun createNetworkRepository(api: DataApi, schedulers: Schedulers) =
            DefaultNetworkRepository(api, schedulers)
}