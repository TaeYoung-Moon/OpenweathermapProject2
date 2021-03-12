package com.example.openweathermap2.comm

import com.example.openweathermap2.model.CityInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    /***********************************************
     *
     * api.openweathermap.org/data/2.5/weather?id=519168&appid=3e019058d36afb29fca9ff3b1a98cfcc
     *
     */
    @GET("weather/")
    open fun getCurrentWeatherData(@Query("id") id: String?, @Query("appid") appid: String?): Call<CityInfo?>?
}