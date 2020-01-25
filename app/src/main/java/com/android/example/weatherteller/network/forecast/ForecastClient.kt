package com.android.example.weatherteller.network.forecast

import com.android.example.weatherteller.network.geocode.geocodeApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ForecastClient(baseUrl: String){
    private val retrofit: Retrofit
    private val moshi: Moshi
    val retrofitService: forecastApiService
    init {
        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(baseUrl)
            .build()
        retrofitService = retrofit.create(forecastApiService::class.java)
    }
}