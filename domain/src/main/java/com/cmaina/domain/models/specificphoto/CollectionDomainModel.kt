package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.photos.DomainPhotoUser

data class CollectionDomainModel(
    val cover_photoDomainModel: CoverPhotoDomainModel,
    val curated: Boolean,
    val description: Any,
    val featured: Boolean,
    val id: String,
    val last_collected_at: String,
    val resultLinksDomainModel: ResultLinksDomainModel,
    val preview_photoDomainModels: List<PreviewPhotoDomainModel>,
    val `private`: Boolean,
    val published_at: String,
    val share_key: String,
    val tags: List<Tag>,
    val title: String,
    val total_photos: Int,
    val updated_at: String,
    val user: DomainPhotoUser
)
