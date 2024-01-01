package com.cmaina.network.models.photos

data class PreviewPhoto(
    val blur_hash: String,
    val created_at: String,
    val id: String,
    val slug: String,
    val updated_at: String,
    val urls: UrlsX
)