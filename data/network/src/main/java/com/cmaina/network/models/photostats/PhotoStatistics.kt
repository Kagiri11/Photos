package com.cmaina.network.models.photostats

data class PhotoStatistics(
    val downloads: Downloads,
    val id: String,
    val likes: Likes,
    val views: Views
)