package com.rohan.currencyconverter.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val baseUrl = "https://api.exchangeratesapi.io"

val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(baseUrl)
    .build()

interface CurrencyService {
    @GET("latest")
    suspend fun getCurrency() : Currency


}
object CurrencyApi {

    val retrofitService : CurrencyService by lazy {
        retrofit.create(CurrencyService::class.java)
    }
}
