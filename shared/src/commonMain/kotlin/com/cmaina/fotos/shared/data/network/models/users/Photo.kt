package com.cmaina.network.models.users

import com.cmaina.fotos.shared.data.network.models.photos.Urls

data class Photo(
    val blur_hash: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val urls: Urls
)