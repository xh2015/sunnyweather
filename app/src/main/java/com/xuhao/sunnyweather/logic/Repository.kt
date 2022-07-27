package com.xuhao.sunnyweather.logic

import androidx.lifecycle.liveData
import com.xuhao.sunnyweather.logic.dao.PlaceDao
import com.xuhao.sunnyweather.logic.model.Place
import com.xuhao.sunnyweather.logic.model.Weather
import com.xuhao.sunnyweather.logic.network.SunnyWeatherNetwork
import com.xuhao.sunnyweather.logic.network.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.Exception
import kotlin.coroutines.CoroutineContext

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:
 */
object Repository {
//    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
//        val result = try {
//            val placeResponse = SunnyWeatherNetwork.searchPlace(query)
//            if (placeResponse.status == "ok") {
//                val place = placeResponse.places
//                Result.success(place)
//            } else {
//                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
//            }
//        } catch (e: Exception) {
//            Result.failure<List<Place>>(e)
//        }
//
//        emit(result)
//    }

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetwork.searchPlace(query)
        if (placeResponse.status == "ok") {
            val place = placeResponse.places
            Result.success(place)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

//    fun refreshWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
//        val result = try {
//            coroutineScope {
//                val deferredRealtime = async {
//                    SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
//                }
//
//                val deferredDaily = async {
//                    SunnyWeatherNetwork.getDailyWeather(lng, lat)
//                }
//
//                val realtimeResponse = deferredRealtime.await()
//                val dailyResponse = deferredDaily.await()
//                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
//                    val weather = Weather(realtimeResponse.realtime, dailyResponse.result.daily)
//                    Result.success(weather)
//                } else {
//                    Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status} daily response status is ${dailyResponse.status}"))
//                }
//            }
//
//        } catch (e: Exception) {
//            Result.failure<Weather>(e)
//        }
//
//        emit(result)
//    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
            }

            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }

            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status} daily response status is ${dailyResponse.status}"))
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }

            emit(result)
        }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}