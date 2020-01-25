package com.android.example.weatherteller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    init {
        _test.value = ""
    }
    private fun getGeoCodeUrl(location: String): String{
        val first = "https://api.mapbox.com/geocoding/v5/mapbox.places/"
        val last = ".json/"
        return first + location + last
    }
    fun locationEntered(location: String){
        val geoCode: String =  getGeoCodeUrl(location)
        getLatLong(geoCode)
    }
    private fun getLatLong(geoCode: String){
        coroutineScope.launch {
            val geoCodeClient = GeocodeClient(geoCode)
            var getDeferred = geoCodeClient.retrofitService.getLatLong("pk.eyJ1IjoiZHVrZS1hbGlidWJ1IiwiYSI6ImNrM3pqdHZpejBkdzczbGw5eG1oN3IzcnUifQ.wRfkb12h2UF7fF_d8tQ5vA")
            try {
                var testResult = getDeferred.await()
                _test.value = "Latitude: ${testResult.features[0].center[1]} \n" +
                            "Longtitude: ${testResult.features[0].center[0]}"
            }
            catch (e: Exception){
                _test.value = e.toString()
            }
        }
    }
}