package com.example.openweathermap2.util

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

object JsonUtil {
    fun getJsonFromAssets(context: Context?, fileName: String?): String? {
        return try {
            val `is` = context!!.getAssets().open(fileName!!)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}