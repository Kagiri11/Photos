package com.cmaina.fotos.shared.domain.models.photos


data class Photo(
    val id: String,
    val blurHash: String,
    val description: String,
    val photoUrls: com.cmaina.fotos.shared.domain.models.photos.PhotoUrls,
    val likedByUser: Boolean,
    val likes: Int,
    val user: com.cmaina.fotos.shared.domain.models.photos.PhotoUser,
    val relatedPhotos: List<Pair<String, String>>
)


