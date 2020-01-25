package com.android.example.weatherteller.network.forecast

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface forecastApiService {
    @GET(".")
    fun getForecast(): Deferred<Forecast>
}