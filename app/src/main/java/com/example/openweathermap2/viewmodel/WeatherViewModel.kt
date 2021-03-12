package com.example.openweathermap2.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.openweathermap2.comm.Config
import com.example.openweathermap2.comm.RetrofitClient
import com.example.openweathermap2.listener.OnIntentListener
import com.example.openweathermap2.model.City
import com.example.openweathermap2.model.CityInfo
import com.example.openweathermap2.model.DescriptionKo
import com.example.openweathermap2.model.WindType
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class WeatherViewModel : ViewModel() {
    private var onIntentListener: OnIntentListener? = null
    private val mCity: City? = City()
    fun setOnIntentListener(onIntentListener: OnIntentListener?) {
        this.onIntentListener = onIntentListener
    }

    fun getCurrentWeatherData(id: String?) {
        val call: Call<CityInfo?>? = RetrofitClient.getInstance()?.getApiService()
                ?.getCurrentWeatherData(id, Config.API_KEY)
        call?.enqueue(object : Callback<CityInfo?> {
            override fun onResponse(call: Call<CityInfo?>?, response: Response<CityInfo?>?) {
                if (response?.code() == 404) {
                    return
                } else {
                    val cityInfo = response?.body()
                    Logger.d("## onResponse ==> " + cityInfo.toString())
                    val mIconUrl =
                            Config.WEATHER_ICON_BASE_URL + cityInfo?.weathers?.get(0)?.icon + Config.WEATHER_ICON_EXTENSION
                    Logger.d("## mIconUrl ==> $mIconUrl")
                    initCityInfo(cityInfo, mIconUrl)
                }
            }

            override fun onFailure(call: Call<CityInfo?>?, t: Throwable?) {
                Logger.e("## onFailure ==> " + t?.message)
            }
        })
    }

    fun initCityInfo(cityInfo: CityInfo?, url: String?) {
        Logger.d("## initCityInfo cityInfo ==> $cityInfo")
        country?.set(mCity?.getCountryName(cityInfo?.sys?.country))
        description?.set(getDescriptionKo(cityInfo?.weathers?.get(0)?.description))
        temp?.set("${cityInfo!!.main?.temp?.let { tempConversion(it) }!!.roundToInt()} ℃")
        feelsLike?.set("Feels like ${(cityInfo!!.main?.feelsLike?.let { tempConversion(it) }!!).roundToInt()} ℃")
        city?.set(cityInfo?.name)
        minMax?.set("min " + cityInfo?.main?.tempMin?.let { tempConversion(it).roundToInt() } + " ℃" + " / " + "max " + cityInfo?.main?.tempMax?.let { tempConversion(it) }?.let { it.roundToInt() } + " ℃")
        minMax?.set("min ${cityInfo?.main?.tempMin?.let { tempConversion(it).roundToInt() }} ℃ / max ${cityInfo?.main?.tempMax?.let { tempConversion(it) }?.let { it.roundToInt() }} ℃")
        pressure?.set(" :    ${cityInfo?.main?.pressure}hpa")
        humidity?.set(" :    ${cityInfo?.main?.humidity}%")
        visibility?.set(" :    ${cityInfo?.visibility?.let { meterToKilometer(it) }}km")
        speedDeg.set(" :     ${cityInfo?.wind?.speed}m/s ${cityInfo?.wind?.deg?.let { getWindDirection(it) }}")
        iconUrl?.set(url)

        onIntentListener?.onIntent()
    }

    /**
     * 켈빈 온도 -> 섭씨 온도로 변환
     *
     * @param temp
     * @return
     */
    private fun tempConversion(temp: Double): Double {
        val kelvin = 273.15
        return temp - kelvin
    }

    private fun meterToKilometer(meter: Float): Double {
        return (meter * 0.001)
    }

    /**
     * 풍향 공식 계산을 통한 값 도출
     *
     * @param degree
     * @return
     */
    private fun getWindDirection(degree: Int): String? {
        val result = ((degree + 22.5 * 0.5) / 22.5).toInt()
        val windType: WindType? = WindType.value(result)
        return windType?.direction
    }

    /**
     * 날씨 상세정보 한글로 변환
     * @param description
     * @return
     */
    private fun getDescriptionKo(description: String?): String? {
        val descriptionKo: DescriptionKo? = DescriptionKo.value(description)
        return descriptionKo?.kr
    }


    companion object {
        @JvmStatic
        var description: ObservableField<String> = ObservableField()

        @JvmStatic
        var temp: ObservableField<String> = ObservableField()

        @JvmStatic
        var feelsLike: ObservableField<String> = ObservableField()

        @JvmStatic
        var country: ObservableField<String> = ObservableField()

        @JvmStatic
        var city: ObservableField<String> = ObservableField()

        @JvmStatic
        var minMax: ObservableField<String> = ObservableField()

        @JvmStatic
        var pressure: ObservableField<String> = ObservableField()

        @JvmStatic
        var humidity: ObservableField<String> = ObservableField()

        @JvmStatic
        var visibility: ObservableField<String> = ObservableField()

        @JvmStatic
        var speedDeg: ObservableField<String> = ObservableField()

        @JvmStatic
        var iconUrl: ObservableField<String> = ObservableField()


        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(imageView: ImageView?, imageUrl: String?) {
            imageView?.let {
                Glide.with(imageView.context)
                        .asBitmap()
                        .onlyRetrieveFromCache(true)
                        .load(imageUrl)
                        .into(imageView)

            }
        }
    }
}