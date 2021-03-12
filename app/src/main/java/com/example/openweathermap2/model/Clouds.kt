package com.example.openweathermap2.model

import com.google.gson.annotations.SerializedName

class Clouds {
    @SerializedName("all")
    private var all = 0f


    override fun toString(): String {
        return "Clouds{" +
                "all=" + all +
                '}'
    }
}