package com.cmaina.network.models.users

import com.cmaina.network.models.Urls

data class Photo(
    val blur_hash: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val urls: Urls
)