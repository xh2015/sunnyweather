package com.xuhao.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:
 */
data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)