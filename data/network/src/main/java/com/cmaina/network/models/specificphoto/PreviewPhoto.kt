package com.cmaina.network.models.specificphoto

import com.cmaina.network.models.photos.Urls

data class PreviewPhoto(
    val blur_hash: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val urls: Urls
)