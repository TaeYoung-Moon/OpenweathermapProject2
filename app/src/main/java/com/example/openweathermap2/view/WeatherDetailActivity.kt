package com.example.openweathermap2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.example.openweathermap2.R
import com.example.openweathermap2.databinding.ActivityWeatherDetailBinding
import com.example.openweathermap2.viewmodel.WeatherViewModel

class WeatherDetailActivity : AppCompatActivity() {
    private var weatherViewModel: WeatherViewModel? = null
    private var viewModelFactory: AndroidViewModelFactory? = null
    private var binding // 데이터 바인딩 작업
            : ActivityWeatherDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        if (viewModelFactory == null) {
            viewModelFactory = AndroidViewModelFactory.getInstance(application)
        }
        weatherViewModel = ViewModelProvider(this, viewModelFactory!!).get(WeatherViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_detail)
        binding!!.viewModel = weatherViewModel
        binding!!.executePendingBindings()
    }
}