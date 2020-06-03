package com.rohan.currencyconverter.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency (@field:Json(name = "rates") val rates:Map<String,String>?,
                     @field:Json(name = "base") val base:String?,
                     @field:Json(name = "date") val date:String?)