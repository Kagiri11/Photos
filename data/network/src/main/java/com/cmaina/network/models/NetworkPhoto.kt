package com.cmaina.network.models

import com.google.gson.annotations.SerializedName

data class NetworkPhoto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("camera")
    val networkCamera: NetworkCamera,
    @SerializedName("earth_date")
    val earthDate: String,
    @SerializedName("img_src")
    val imgSrc: String,
    @SerializedName("rover")
    val networkRover: NetworkRover,
    @SerializedName("sol")
    val sol: Int
)
