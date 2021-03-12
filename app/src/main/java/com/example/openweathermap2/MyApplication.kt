package com.example.openweathermap2

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.Logger

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val context: Context = this
        // 디버그 시에만 Log 출력
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}