package com.cmaina.domain.models.search

import com.cmaina.domain.models.photos.DomainPhotoLinks
import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.photos.DomainUrls

data class SearchedPhotoDomainModel(
    val altDescription: String?,
    val blurHash: String?,
    val description: String?,
    val id: String?,
    val likedByUser: Boolean?,
    val likes: Int?,
    val links: DomainPhotoLinks?,
    val urls: DomainUrls?,
    val user: DomainPhotoUser?,
)
