package com.cmaina.domain.models.users

import com.cmaina.domain.models.photos.DomainUrls

data class UserPhotoDomainModel(
    val blur_hash: String?,
    val created_at: String?,
    val id: String?,
    val updated_at: String?,
    val urls: DomainUrls?
)
