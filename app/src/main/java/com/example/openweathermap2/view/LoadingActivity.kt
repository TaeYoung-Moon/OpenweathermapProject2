package com.example.openweathermap2.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.openweathermap2.R
import com.example.openweathermap2.databinding.AcitivtyLoadingBinding
import com.example.openweathermap2.listener.OnIntentListener
import com.example.openweathermap2.viewmodel.LoadingViewModel
import com.example.openweathermap2.viewmodel.WeatherViewModel

class LoadingActivity : AppCompatActivity(), OnIntentListener {
    private var weatherViewModel: WeatherViewModel? = null
    private var loadingViewModel: LoadingViewModel? = null
    private var viewModelFactory: AndroidViewModelFactory? = null
    private var binding // 데이터 바인딩 작업
            : AcitivtyLoadingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        if (viewModelFactory == null) {
            viewModelFactory = AndroidViewModelFactory.getInstance(application)
        }
        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        loadingViewModel = ViewModelProvider(this)[LoadingViewModel::class.java]

        weatherViewModel?.setOnIntentListener(this)
        binding = DataBindingUtil.setContentView(this, R.layout.acitivty_loading)
        binding!!.setViewModel(loadingViewModel)
        binding!!.executePendingBindings()
        Glide.with(this)
                .asGif()
                .load(R.raw.loading)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE) // Glide 에서 캐싱한 리소스와 로드할 리소스가 같을때 캐싱된 리소스 사용
                .into(binding!!.loadingImage)

        val intent = intent
        val id = intent.getStringExtra("id")
        weatherViewModel!!.getCurrentWeatherData(id)
    }

    override fun onIntent() {
        val intent = Intent(this, WeatherDetailActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        finish()
    }
}