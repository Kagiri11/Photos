package com.cmaina.network.models.specificphoto

import com.cmaina.network.models.photos.User

data class Result(
    val cover_photo: CoverPhoto,
    val curated: Boolean,
    val description: Any,
    val featured: Boolean,
    val id: String,
    val last_collected_at: String,
    val resultLinks: ResultLinks,
    val preview_photos: List<PreviewPhoto>,
    val `private`: Boolean,
    val published_at: String,
    val share_key: String,
    val tags: List<Tag>,
    val title: String,
    val total_photos: Int,
    val updated_at: String,
    val user: User
)
