package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.photos.DomainPhotoLinks
import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.photos.DomainUrls
import com.cmaina.domain.models.search.TopicSubmissionsDomainModel

data class CoverPhotoDomainModel(
    val alt_description: String,
    val blur_hash: String,
    val categories: List<Any>,
    val color: String,
    val created_at: String,
    val description: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: DomainPhotoLinks,
    val urls: DomainUrls,
    val user: DomainPhotoUser,
    val width: Int
)
