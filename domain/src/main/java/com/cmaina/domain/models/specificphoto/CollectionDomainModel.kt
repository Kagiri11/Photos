package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.photos.DomainPhotoUser

data class CollectionDomainModel(
    val description: String?,
    val id: String?,
    val resultLinksDomainModel: ResultLinksDomainModel?,
    val previewPhotoDomainModels: List<PreviewPhotoDomainModel>?,
    val title: String?,
    val totalPhotos: Int?,
    val user: DomainPhotoUser?
)
