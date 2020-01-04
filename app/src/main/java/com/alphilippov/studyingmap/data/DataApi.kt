package com.alphilippov.studyingmap.data


import com.alphilippov.studyingmap.data.model.CoursesData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface DataApi {



    @GET("courses")
    fun getCourses(@Query("page") page: Int,
                   @Query("page_size") page_size: Int,
                   @Query("search") search: String,
                   @Query("price") price: String,
                   @Query("is_affiliate_agreed") aff: Boolean,
                   @Query("language") lang: String,
                   @Query("instructional_level") level: String,
                   @Query("ordering") order: String,
                   @Query("ratings") ratings: Int):Single<CoursesData>
}


