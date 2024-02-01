package com.cmaina.network.models.users

import com.cmaina.fotos.shared.data.network.models.specificphoto.Tag
import com.google.gson.annotations.SerializedName


data class Tags(
    @SerializedName("aggregated")
    val aggregated: List<Tag>,
    @SerializedName("custom")
    val custom: List<Custom>
)