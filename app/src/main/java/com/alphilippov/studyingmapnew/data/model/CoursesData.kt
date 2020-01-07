package com.alphilippov.studyingmapnew.data.model

import com.google.gson.annotations.SerializedName


data class CoursesData(
        @field:SerializedName("count") val count:Int,
        @field:SerializedName("next") val next:String,
        @field:SerializedName("results")val result:List<CoursesList>


){
}