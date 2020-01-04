package com.alphilippov.studyingmap.data


import com.alphilippov.studyingmap.data.model.FootballHockey
import io.reactivex.Single

class DefaultRepository(
        private val networkRepository: NetworkRepository

        ) : Repository {
    override fun getBets(
        apiKey: String,
        sport: String,
        region: String,
        mkt: String
    ): Single<FootballHockey> =
        networkRepository.getBets(apiKey, sport, region, mkt)




}