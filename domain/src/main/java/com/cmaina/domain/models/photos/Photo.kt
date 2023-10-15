package com.cmaina.domain.models.photos

data class Photo(
    val id: String,
    val blurHash: String,
    val description: String,
    val domainUrls: DomainUrls,
    val likedByUser: Boolean,
    val likes: Int
)
