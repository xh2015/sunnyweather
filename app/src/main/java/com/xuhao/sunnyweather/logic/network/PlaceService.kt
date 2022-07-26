package com.xuhao.sunnyweather.logic.network

import com.xuhao.sunnyweather.SunnyWeatherApplication
import com.xuhao.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:
 */
interface PlaceService {
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}