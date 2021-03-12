package com.example.openweathermap2.model

import com.google.gson.annotations.SerializedName

class Wind {
    @SerializedName("speed")
    var speed = 0f

    @SerializedName("deg")
    var deg = 0

    @SerializedName("gust")
    var gust = 0f
    override fun toString(): String {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                ", gust=" + gust +
                '}'
    }
}