package com.alphilippov.studyingmapnew.data.model

import com.google.gson.annotations.SerializedName


data class InstructorCourse(
        @field:SerializedName("title") val titleInstructor: String,
        @field:SerializedName("image_50x50") val avatarInstructor50x: String,
        @field:SerializedName("job_title") val jobInstructor: String,
        @field:SerializedName("url") val urlInstructor: String,
        @field:SerializedName("image_100x100") val avatarInstructor100x: String,
        @field:SerializedName("_class") val _class: String,
        @field:SerializedName("display_name") val displayName: String,
        @field:SerializedName("initials") val initialsInstructor: String,
        @field:SerializedName("name") val name: String
)  {
}