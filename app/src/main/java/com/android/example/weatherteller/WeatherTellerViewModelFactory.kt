package com.android.example.weatherteller

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherTellerViewModelFactory (private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherTellerViewModel::class.java)) {
            return WeatherTellerViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}