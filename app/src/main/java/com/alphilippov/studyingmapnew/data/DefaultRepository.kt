package com.alphilippov.studyingmapnew.data


import com.alphilippov.studyingmapnew.data.model.CoursesData
import io.reactivex.Single

class DefaultRepository(
        private val networkRepository: NetworkRepository

) : Repository {
    override fun getCourses(page: Int, page_size: Int, search: String, price: String, aff: Boolean, lang: String, level: String, order: String, ratings: Int): Single<CoursesData>
            = networkRepository.getCourses(page, page_size, search, price, aff, lang, level, order, ratings)
}