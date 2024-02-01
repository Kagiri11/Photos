package com.cmaina.fotos.shared.data.network.models.photos

import com.google.gson.annotations.SerializedName

data class Urls(
    @SerializedName("full")
    val full: String?,
    @SerializedName("raw")
    val raw: String?,
    @SerializedName("regular")
    val regular: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("small_s3")
    val small_s3: String?,
    @SerializedName("thumb")
    val thumb: String?
)
