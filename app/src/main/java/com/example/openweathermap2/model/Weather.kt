package com.example.openweathermap2.model

import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("main")
    var main: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("icon")
    var icon: String? = null
    override fun toString(): String {
        return "Weather{" +
                "id='" + id + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}'
    }

}