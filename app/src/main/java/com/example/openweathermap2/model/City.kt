package com.example.openweathermap2.model

import com.example.openweathermap2.util.StringUtil
import java.util.*

class City {

    var id: String? = null
    var name: String? = null
    var country: String? = null
    var coord: Coord? = null
    override fun toString(): String {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", coord=" + coord +
                '}'
    }

    fun getCountryName(country: String?): String? {
        return if (StringUtil.getNvlStr(country, "")?.isEmpty() == true) {
            "대륙 (Continent)"
        } else {
            val locale = Locale("", country)
            locale.displayCountry + " (" + country + ")"
        }
    }
}