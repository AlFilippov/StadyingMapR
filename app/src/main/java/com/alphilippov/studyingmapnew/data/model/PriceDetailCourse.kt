package com.alphilippov.studyingmapnew.data.model

import com.google.gson.annotations.SerializedName


class PriceDetailCourse(
      @field:SerializedName("currency")  val currency:String,
      @field:SerializedName("currency_symbol") val currencySymbol:String,
      @field:SerializedName("price_string") val price:String,
      @field:SerializedName("amount") val amount:Float

) {
}
