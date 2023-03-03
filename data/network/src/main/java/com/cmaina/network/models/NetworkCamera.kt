package com.cmaina.network.models

import com.google.gson.annotations.SerializedName

data class NetworkCamera(
    @SerializedName("id")
    val id: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rover_id")
    val roverId: Int
)
