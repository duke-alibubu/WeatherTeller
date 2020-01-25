package com.android.example.weatherteller.network.forecast

data class Forecast (
    val daily: DailyUpdate,
    val currently: Temp
)

data class DailyUpdate (
    val summary: String
)

data class Temp (
    val temperature: Float,
    val precipProbability: Float
)