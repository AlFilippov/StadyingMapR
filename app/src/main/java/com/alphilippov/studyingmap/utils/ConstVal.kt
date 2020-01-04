package com.alphilippov.studyingmap.utils

import com.alphilippov.studyingmap.BuildConfig
import com.google.common.io.BaseEncoding

object ConstVal {
    var PAGE_SIZE = 50
    var PRICE = "price-paid"
    var AFFILIATE = true
    var LEVEL_COURSES = "beginner"
    var ORDERING = "highest-rated"
    var RATINGS = 4
    var LANGUAGE = "en"

    fun getBasicAuthenticator(): String {
        return BaseEncoding.base64().encode((BuildConfig.UDEMY_ID + ":" + BuildConfig.UDEMY_SECRET).toByteArray())
    }
}