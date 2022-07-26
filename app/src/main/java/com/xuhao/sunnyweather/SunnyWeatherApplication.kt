package com.xuhao.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:
 */
class SunnyWeatherApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "U1oSHrqCYpez1065"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}