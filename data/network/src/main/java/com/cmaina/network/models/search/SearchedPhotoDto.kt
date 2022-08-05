package com.cmaina.network.models.search

import com.cmaina.network.models.photos.PhotoLinks
import com.cmaina.network.models.photos.Urls
import com.cmaina.network.models.photos.User

data class SearchedPhotoDto(
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
    val links: PhotoLinks,
    val promoted_at: String,
    val sponsorship: Any,
    val topic_submissions: TopicSubmissions,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val width: Int
)
