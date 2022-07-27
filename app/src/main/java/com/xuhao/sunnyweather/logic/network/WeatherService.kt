package com.xuhao.sunnyweather.logic.network

import com.xuhao.sunnyweather.SunnyWeatherApplication
import com.xuhao.sunnyweather.logic.model.DailyResponse
import com.xuhao.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:
 */
interface WeatherService {
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}