package com.example.openweathermap2.model

import com.google.gson.annotations.SerializedName

class Coord {
    @SerializedName("lon")
    private var lon = 0f

    @SerializedName("lat")
    private var lat = 0f
    override fun toString(): String {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}'
    }


}