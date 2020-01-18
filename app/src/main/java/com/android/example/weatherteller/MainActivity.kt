package com.android.example.weatherteller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.example.weatherteller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: WeatherTellerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModelFactory: WeatherTellerViewModelFactory = WeatherTellerViewModelFactory(application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherTellerViewModel::class.java)
    }
}
