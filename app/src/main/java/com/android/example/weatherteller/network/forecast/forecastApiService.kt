package com.android.example.weatherteller.network.forecast

import com.android.example.weatherteller.network.geocode.LatLongProperty
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface forecastApiService {
    @GET(".")
    fun getForecast(): Deferred<LatLongProperty>
}