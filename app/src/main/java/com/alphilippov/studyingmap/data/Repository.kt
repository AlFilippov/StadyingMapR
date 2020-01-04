package com.alphilippov.studyingmap.data



import com.alphilippov.studyingmap.data.model.FootballHockey
import io.reactivex.Single

interface Repository {
    fun getBets(
        apiKey: String,
        sport: String,
        region: String,
        mkt: String
    ): Single<FootballHockey>

}