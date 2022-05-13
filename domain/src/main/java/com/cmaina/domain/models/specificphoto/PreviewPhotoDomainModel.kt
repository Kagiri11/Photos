package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.photos.DomainUrls

data class PreviewPhotoDomainModel(
    val blur_hash: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val urls: DomainUrls
)
