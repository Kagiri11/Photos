package com.cmaina.network.models.users

import com.cmaina.network.models.photos.Social
import com.cmaina.network.models.photos.SponsorLinks
import com.cmaina.network.models.specificphoto.Meta
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("accepted_tos")
    val accepted_tos: Boolean,
    @SerializedName("allow_messages")
    val allow_messages: Boolean,
    @SerializedName("badge")
    val badge: Any,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("followed_by_user")
    val followed_by_user: Boolean,
    @SerializedName("followers_count")
    val followers_count: Int,
    @SerializedName("following_count")
    val following_count: Int,
    @SerializedName("for_hire")
    val for_hire: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("instagram_username")
    val instagram_username: String,
    @SerializedName("last_name")
    val last_name: Any,
    @SerializedName("links")
    val links: SponsorLinks,
    @SerializedName("location")
    val location: Any,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("name")
    val name: String,
    @SerializedName("numeric_id")
    val numeric_id: Int,
    @SerializedName("photos")
    val photos: List<Photo>,
    @SerializedName("portfolio_url")
    val portfolio_url: String,
    @SerializedName("profile_image")
    val profile_image: ProfileImage,
    @SerializedName("social")
    val social: Social,
    @SerializedName("tags")
    val tags: Tags? = null,
    @SerializedName("total_collections")
    val total_collections: Int,
    @SerializedName("total_likes")
    val total_likes: Int,
    @SerializedName("total_photos")
    val total_photos: Int,
    @SerializedName("twitter_username")
    val twitter_username: String,
    @SerializedName("updated_at")
    val updated_at: String,
    @SerializedName("username")
    val username: String
)