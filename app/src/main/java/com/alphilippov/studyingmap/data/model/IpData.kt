package com.alphilippov.studyingmap.data.model

import com.google.gson.annotations.SerializedName

data class IpData(
    @field:SerializedName("ip") val ip: String
) {
}