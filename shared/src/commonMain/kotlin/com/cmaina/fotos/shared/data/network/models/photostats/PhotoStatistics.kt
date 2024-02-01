package com.cmaina.fotos.shared.data.network.models.photostats

data class PhotoStatistics(
    val downloads: Downloads,
    val id: String,
    val likes: Likes,
    val views: Views
)