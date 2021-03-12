package com.example.openweathermap2.model

import com.google.gson.annotations.SerializedName

class Sys {
    @SerializedName("type")
    var type = 0f

    @SerializedName("id")
    var id = 0f

    @SerializedName("country")
    var country: String? = null

    @SerializedName("sunrise")
    var sunrise = 0f

    @SerializedName("sunset")
    var sunset = 0f
    override fun toString(): String {
        return "Sys{" +
                "type=" + type +
                ", id=" + id +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}'
    }


}