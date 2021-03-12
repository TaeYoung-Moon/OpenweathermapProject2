package com.example.openweathermap2.model

import com.google.gson.annotations.SerializedName

class CityInfo {
    @SerializedName("coord")
    var coord: Coord? = null

    @SerializedName("weather")
    var weathers: MutableList<Weather?>? = null

    @SerializedName("base")
    var base: String? = null

    @SerializedName("main")
    var main: Main? = null

    @SerializedName("visibility")
    var visibility = 0f

    @SerializedName("wind")
    var wind: Wind? = null

    @SerializedName("clouds")
    var clouds: Clouds? = null

    @SerializedName("dt")
    var dt: String? = null

    @SerializedName("sys")
    var sys: Sys? = null

    @SerializedName("timezone")
    var timezone: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("cod")
    var cod: String? = null
    override fun toString(): String {
        return "CityInfo{" +
                "coord=" + coord +
                ", weathers=" + weathers +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility='" + visibility + '\'' +
                ", Wind=" + wind +
                ", clouds=" + clouds +
                ", dt='" + dt + '\'' +
                ", sys=" + sys +
                ", timezone='" + timezone + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cod='" + cod + '\'' +
                '}'
    }

}