package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.photos.DomainPhotoUser

data class CollectionDomainModel(
    val cover_photoDomainModel: CoverPhotoDomainModel?,
    val description: Any?,
    val id: String?,
    val resultLinksDomainModel: ResultLinksDomainModel?,
    val preview_photoDomainModels: List<PreviewPhotoDomainModel>?,
    val share_key: String?,
    val tagDomainModels: List<TagDomainModel>?,
    val title: String?,
    val total_photos: Int?,
    val updated_at: String?,
    val user: DomainPhotoUser?
)
