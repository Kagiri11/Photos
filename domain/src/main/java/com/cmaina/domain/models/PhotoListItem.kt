package com.cmaina.domain.models

data class PhotoListItem(
    val altDescription: String,
    val blurHash: String,
    val categories: List<Any>,
    val color: String,
    val created_at: String,
    val currentUserCollections: List<Any>,
    val description: String,
    val height: Int,
    val id: String,
    val likedByUser: Boolean,
    val likes: Int,
    val links: PhotoLinks,
    val promotedAt: String,
    val sponsorship: Sponsorship,
    val topicSubmissions: TopicSubmissions,
    val updatedAt: String,
    val urls: Urls,
    val user: User,
    val width: Int
)
