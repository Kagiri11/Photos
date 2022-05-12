package com.cmaina.network.models.photostats

import com.cmaina.domain.models.photostats.Likes

data class PhotoStatistics(
    val downloads: Downloads,
    val id: String,
    val likes: Likes,
    val views: Views
)