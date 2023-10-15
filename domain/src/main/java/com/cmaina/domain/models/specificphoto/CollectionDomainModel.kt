package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.users.User

data class CollectionDomainModel(
    val description: String,
    val id: String,
    val previewPhotos: List<PreviewPhoto>,
    val title: String?,
    val totalPhotos: Int?,
    val user: User
)
