package com.alphilippov.studyingmapnew.data


import com.alphilippov.studyingmapnew.data.model.CoursesData
import com.alphilippov.studyingmapnew.utils.Schedulers
import io.reactivex.Single

class DefaultNetworkRepository(
        private val api: DataApi,
        private val schedulers: Schedulers
) : NetworkRepository {
    override fun getCourses(page: Int, page_size: Int, search: String, price: String, aff: Boolean, lang: String, level: String, order: String, ratings: Int): Single<CoursesData>
            = api.getCourses(page, page_size, search, price, aff, lang, level, order, ratings)


}