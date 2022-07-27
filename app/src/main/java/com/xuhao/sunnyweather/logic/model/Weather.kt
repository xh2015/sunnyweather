package com.xuhao.sunnyweather.logic.model

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)
