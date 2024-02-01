package com.cmaina.fotos.shared.domain.models.users

import com.cmaina.fotos.shared.domain.models.photos.PhotoUrls

data class UserPhotoDomainModel(
    val blur_hash: String?,
    val created_at: String?,
    val id: String?,
    val updated_at: String?,
    val urls: com.cmaina.fotos.shared.domain.models.photos.PhotoUrls?
)
