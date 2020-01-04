package com.alphilippov.studyingmap.data


import com.alphilippov.studyingmap.data.model.CoursesData
import io.reactivex.Single


interface NetworkRepository {


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