package com.cmaina.fotos.shared.data.network.models.photos

import com.cmaina.fotos.shared.data.network.models.search.TopicSubmissions
import com.cmaina.fotos.shared.data.network.models.specificphoto.Exif
import com.cmaina.fotos.shared.data.network.models.specificphoto.Location
import com.google.gson.annotations.SerializedName

data class PhotoListItem(
    @SerializedName("alt_description")
    val altDescription: String?,
    @SerializedName("blur_hash")
    val blurHash: String?,
    @SerializedName("categories")
    val categories: List<Any>?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: String,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("links")
    val links: PhotoLinks?,
    @SerializedName("promoted_at")
    val promotedAt: String?,
    @SerializedName("sponsorship")
    val sponsorship: Sponsorship?,
    @SerializedName("topic_submissions")
    val topicSubmissions: TopicSubmissions?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("urls")
    val urls: Urls?,
    @SerializedName("user")
    val user: User?,
    @SerializedName("width")
    val width: Int?,
    // you can exclude these
    @SerializedName("exif")
    val exif: Exif?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("views")
    val views: Int?,
    @SerializedName("downloads")
    val downloads: Int?,
    @SerializedName("related_collections")
    val collections: Collections
)
