package com.alphilippov.studyingmap.data


import com.alphilippov.studyingmap.data.model.FootballHockey
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface DataApi {

    //TODO:Запросы с параметрами
    @GET("v3/odds")
    fun getBets(
            @Query("apiKey") apiKey: String,
            @Query("sport") sport: String,
            @Query("region") region: String,
            @Query("mkt") mkt: String
    ): Single<FootballHockey>
}


