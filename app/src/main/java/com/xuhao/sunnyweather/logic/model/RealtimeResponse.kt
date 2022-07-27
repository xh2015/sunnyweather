package com.xuhao.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)

    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}