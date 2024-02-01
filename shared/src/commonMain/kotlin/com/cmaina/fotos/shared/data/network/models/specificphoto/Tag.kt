package com.cmaina.fotos.shared.data.network.models.specificphoto

import com.cmaina.fotos.shared.data.network.models.specificphoto.Source
import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("type")
    val type: String? = null
)
