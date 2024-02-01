package com.cmaina.fotos.shared.data.network.models.specificphoto

import com.cmaina.fotos.shared.data.network.models.photos.Urls

data class PreviewPhoto(
    val blur_hash: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val urls: Urls
)