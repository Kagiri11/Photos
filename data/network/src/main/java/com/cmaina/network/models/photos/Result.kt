package com.cmaina.network.models.photos

data class Result(
    val cover_photo: CoverPhoto,
    val description: Any,
    val featured: Boolean,
    val id: String,
    val published_at: String,
    val share_key: String,
    val title: String,
    val total_photos: Int,
    val updated_at: String
)