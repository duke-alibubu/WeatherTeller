package com.android.example.weatherteller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.example.weatherteller.network.forecast.ForecastClient
import com.android.example.weatherteller.network.geocode.GeocodeClient
import com.android.example.weatherteller.network.geocode.LatLongProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception


class WeatherTellerViewModel(application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _test = MutableLiveData<String>()
    val test: LiveData<String>
        get() = _test
    private val _latitude = MutableLiveData<Float>()
    private val _longtitude = MutableLiveData<Float>()
    init {
        _test.value = ""
    }
    private fun getGeoCodeUrl(location: String): String{
        val first = "https://api.mapbox.com/geocoding/v5/mapbox.places/"
        val last = ".json/"
        return first + location + last
    }

    private fun getForeCastUrl(): String?{
        if (_latitude.value == null || _longtitude.value == null)
            return null
        return "https://api.darksky.net/forecast/ce274fb0aa7b3339f6f77be767a3e8da/" + _latitude.value + ',' + _longtitude.value + '/'
    }
    fun locationEntered(location: String){
        val geoCode: String =  getGeoCodeUrl(location)
        getLatLong(geoCode)
        val forecastUrl: String? = getForeCastUrl()
        forecastUrl?.let{
            getForecast(forecastUrl)
        }
    }
    private fun getLatLong(geoCode: String){
        coroutineScope.launch {
            val geoCodeClient = GeocodeClient(geoCode)
            var getDeferred = geoCodeClient.retrofitService.getLatLong("pk.eyJ1IjoiZHVrZS1hbGlidWJ1IiwiYSI6ImNrM3pqdHZpejBkdzczbGw5eG1oN3IzcnUifQ.wRfkb12h2UF7fF_d8tQ5vA")
            try {
                var testResult = getDeferred.await()
                _latitude.value = testResult.features[0].center[1]
                _longtitude.value = testResult.features[0].center[0]
            }
            catch (e: Exception){
                _test.value = e.toString()
            }
        }
    }
    private fun getForecast(forecastUrl: String){
        coroutineScope.launch {
            val forecastClient = ForecastClient(forecastUrl)
            val getDeferred = forecastClient.retrofitService.getForecast()
            try {
                var result = getDeferred.await()
                _test.value = "${result.daily.data[0].summary}\n" +
                            "It is currently ${result.currently.temperature} degree out.\n" +
                            "There is a ${result.currently.precipProbability}% chance to rain."
            }
            catch (e: Exception){
                _test.value = "Cannot retrieve forecast!"
            }
        }
    }
}