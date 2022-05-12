package com.cmaina.network.models.search

import com.cmaina.network.models.specificphoto.TexturesPatterns
import com.cmaina.network.models.specificphoto.Wallpapers

data class TopicSubmissions(
    val architecture: Architecture,
    val artsCulture: ArtsCulture,
    val colorOfWater: ColorOfWater,
    val history: History,
    val texturesPatterns: TexturesPatterns,
    val wallpapers: Wallpapers
)
