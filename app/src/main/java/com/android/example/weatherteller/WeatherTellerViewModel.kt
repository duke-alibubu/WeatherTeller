package com.android.example.weatherteller

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class WeatherTellerViewModel(application: Application): AndroidViewModel(application) {

    fun getGeoCodeUrl(location: String): String{
        val first = "https://api.mapbox.com/geocoding/v5/mapbox.places/"
        val last = ".json?access_token=pk.eyJ1IjoiZHVrZS1hbGlidWJ1IiwiYSI6ImNrM3pqdHZpejBkdzczbGw5eG1oN3IzcnUifQ.wRfkb12h2UF7fF_d8tQ5vA"
        return first + location + last
    }
    fun locationEntered(location: String){
        val geoCode: String =  getGeoCodeUrl(location)
    }
}