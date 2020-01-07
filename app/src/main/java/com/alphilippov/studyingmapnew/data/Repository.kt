package com.alphilippov.studyingmapnew.data



import com.alphilippov.studyingmapnew.data.model.CoursesData
import io.reactivex.Single

interface Repository {
    fun getCourses(page: Int,
                   page_size: Int,
                   search: String,
                   price: String,
                   aff: Boolean,
                   lang: String,
                   level: String,
                   order: String,
                   ratings: Int): Single<CoursesData>

}