package com.xuhao.sunnyweather.logic

import androidx.lifecycle.liveData
import com.xuhao.sunnyweather.logic.model.Place
import com.xuhao.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.Exception

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:
 */
object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlace(query)
            if (placeResponse.status == "ok") {
                val place = placeResponse.places
                Result.success(place)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }

        emit(result)
    }

}