package com.cmaina.fotos.shared.models.photos


data class Photo(
    val id: String,
    val blurHash: String,
    val description: String,
    val photoUrls: PhotoUrls,
    val likedByUser: Boolean,
    val likes: Int,
    val user: PhotoUser,
    val relatedPhotos: List<Pair<String, String>>
)


