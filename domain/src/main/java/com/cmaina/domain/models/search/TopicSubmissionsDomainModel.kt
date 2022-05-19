package com.cmaina.domain.models.search

import com.cmaina.domain.models.specificphoto.TexturesPatterns
import com.cmaina.domain.models.specificphoto.Wallpapers

data class TopicSubmissionsDomainModel(
    val architecture: Architecture,
    val artsCulture: ArtsCulture,
    val colorOfWater: ColorOfWater,
    val history: History,
    val texturesPatterns: TexturesPatterns,
    val wallpapers: Wallpapers
)
