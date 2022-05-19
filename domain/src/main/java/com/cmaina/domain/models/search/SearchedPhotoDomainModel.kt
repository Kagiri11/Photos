package com.cmaina.domain.models.search

import com.cmaina.domain.models.photos.DomainPhotoLinks
import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.photos.DomainUrls
import com.cmaina.domain.models.specificphoto.Tag

data class SearchedPhotoDomainModel(
    val alt_description: String,
    val blur_hash: String,
    val categories: List<Any>,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: String,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: DomainPhotoLinks,
    val promoted_at: String,
    val sponsorship: Any,
    val tags: List<Tag>,
    val topic_submissions: TopicSubmissionsDomainModel,
    val updated_at: String,
    val urls: DomainUrls,
    val user: DomainPhotoUser,
    val width: Int
)
