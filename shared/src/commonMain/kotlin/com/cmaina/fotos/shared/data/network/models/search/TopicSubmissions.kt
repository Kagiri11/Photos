package com.cmaina.fotos.shared.data.network.models.search

import com.cmaina.fotos.shared.data.network.models.specificphoto.TexturesPatterns
import com.cmaina.fotos.shared.data.network.models.specificphoto.Wallpapers
import com.google.gson.annotations.SerializedName

data class TopicSubmissions(
    @SerializedName("architecture")
    val architecture: Architecture?,
    @SerializedName("artsCulture")
    val artsCulture: ArtsCulture?,
    @SerializedName("colorOfWater")
    val colorOfWater: ColorOfWater?,
    @SerializedName("history")
    val history: History?,
    @SerializedName("texturesPatterns")
    val texturesPatterns: TexturesPatterns?,
    @SerializedName("wallpapers")
    val wallpapers: Wallpapers?
)
