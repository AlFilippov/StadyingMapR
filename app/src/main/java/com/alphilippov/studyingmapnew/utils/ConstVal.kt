package com.alphilippov.studyingmapnew.utils

import android.util.Base64
import com.alphilippov.studyingmapnew.BuildConfig
import java.util.*


object ConstVal {
    var PAGE_SIZE = 50
    var PRICE = "price-paid"
    var AFFILIATE = true
    var LEVEL_COURSES = "beginner"
    var ORDERING = "highest-rated"
    var RATINGS = 4
    var LANGUAGE = "en"

    fun getBasicAuthenticator(): String {
       // return Base64.encode((BuildConfig.UDEMY_ID + ":" + BuildConfig.UDEMY_SECRET).toByteArray())
        return "3"
    }
}