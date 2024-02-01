package com.cmaina.fotos.shared.data.network.models.photos

data class UserLinks(
    val followers: String,
    val following: String,
    val html: String,
    val likes: String,
    val photos: String,
    val portfolio: String,
    val self: String
)