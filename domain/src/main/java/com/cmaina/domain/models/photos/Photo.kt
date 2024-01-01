package com.cmaina.domain.models.photos


data class Photo(
    val id: String,
    val blurHash: String,
    val description: String,
    val photoUrls: PhotoUrls,
    val likedByUser: Boolean,
    val likes: Int,
    val user: PhotoUser,
    val relatedPhotos: List<Map<String, String>>
)

data class PhotoUser(
    val userName: String,
    val userPhotoImageUrl: String,
)
