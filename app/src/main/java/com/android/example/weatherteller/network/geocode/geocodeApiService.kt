package com.android.example.weatherteller.network.geocode

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface geocodeApiService {
    @GET(".")
    fun getLatLong(@Query("access_token") access_token: String): Deferred<LatLongProperty>
}