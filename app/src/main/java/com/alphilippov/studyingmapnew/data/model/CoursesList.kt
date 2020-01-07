package com.alphilippov.studyingmapnew.data.model

import com.google.gson.annotations.SerializedName


data class CoursesList(
        @field:SerializedName("_class") val classes :String,
        @field:SerializedName("id") val id : Int,
        @field:SerializedName("title") val title:String,
        @field:SerializedName("url") val url:String,
        @field:SerializedName("is_paid") val isPaid:Boolean,
        @field:SerializedName("price") val price:String,
        @field:SerializedName("price_detail")val priceDetail: PriceDetailCourse,
        @field:SerializedName("visible_instructor") val instructor:List<InstructorCourse>,
        @field:SerializedName("image_125_H") val image125H:String,
        @field:SerializedName("image_240x135") val image240x135:String,
        @field:SerializedName("image480x720") val image480x720:String,
        @field:SerializedName("is_practice_test_course") val isPracticeTestCourse:Boolean,
        @field:SerializedName("published_title") val publishedTitle:String,
        @field:SerializedName("relevancy_score") val relevancyScore:Float
) {
}