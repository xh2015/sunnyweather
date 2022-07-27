package com.xuhao.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.xuhao.sunnyweather.SunnyWeatherApplication
import com.xuhao.sunnyweather.logic.model.Place

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:
 */
object PlaceDao {

    fun savePlace(place: Place) {
        sharePreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharePreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharePreferences().contains("place")

    private fun sharePreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}