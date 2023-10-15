package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.photos.DomainPhotoLinks
import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.photos.PhotoUrls

data class CoverPhotoDomainModel(
    val alt_description: String?,
    val blur_hash: String?,
    val categories: List<Any>?,
    val color: String?,
    val created_at: String?,
    val description: String?,
    val height: Int?,
    val id: String?,
    val liked_by_user: Boolean?,
    val likes: Int?,
    val links: DomainPhotoLinks?,
    val urls: PhotoUrls?,
    val user: DomainPhotoUser?,
    val width: Int?
)
