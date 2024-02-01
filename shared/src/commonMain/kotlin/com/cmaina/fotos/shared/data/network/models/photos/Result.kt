package com.cmaina.fotos.shared.data.network.models.photos

import com.cmaina.fotos.shared.data.network.models.photos.CoverPhoto
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("cover_photo")
    val coverPhoto: CoverPhoto,
    @SerializedName("description")
    val description: Any,
    @SerializedName("featured")
    val featured: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("share_key")
    val shareKey: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_photos")
    val totalPhotos: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)