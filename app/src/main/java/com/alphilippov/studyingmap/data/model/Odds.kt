package com.alphilippov.studyingmap.data.model

import com.google.gson.annotations.SerializedName

data class Odds(
    @field:SerializedName("h2h") val h2h:List<Double>
) {
}