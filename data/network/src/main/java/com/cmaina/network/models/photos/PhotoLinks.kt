package com.cmaina.network.models.photos

import com.google.gson.annotations.SerializedName

data class PhotoLinks(
    @SerializedName("download")
    val download: String?,
    @SerializedName("download_location")
    val download_location: String?,
    @SerializedName("html")
    val html: String?,
    @SerializedName("self")
    val self: String?
)