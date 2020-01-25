package com.android.example.weatherteller.network.geocode

data class LatLongProperty(
    val type: String,
    val features: List<CenterObj> = listOf()
)

data class CenterObj (
    val center: List<Float> = listOf()
)