package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.photos.DomainUrls

data class PreviewPhotoDomainModel(
    val blur_hash: String?,
    val id: String?,
    val urls: DomainUrls?
)
