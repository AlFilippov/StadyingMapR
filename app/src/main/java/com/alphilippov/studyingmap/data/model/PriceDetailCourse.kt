package com.alphilippov.studyingmap.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


class PriceDetailCourse(
      @field:SerializedName("currency")  val currency:String,
      @field:SerializedName("currency_symbol") val currencySymbol:String,
      @field:SerializedName("price_string") val price:String,
      @field:SerializedName("amount") val amount:Float

) {
}
