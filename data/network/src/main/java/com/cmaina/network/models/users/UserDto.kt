package com.cmaina.network.models.users

import com.cmaina.network.models.Social
import com.cmaina.network.models.SponsorLinks
import com.cmaina.network.models.specificphoto.Meta

data class UserDto(
    val accepted_tos: Boolean,
    val allow_messages: Boolean,
    val badge: Any,
    val bio: String,
    val downloads: Int,
    val first_name: String,
    val followed_by_user: Boolean,
    val followers_count: Int,
    val following_count: Int,
    val for_hire: Boolean,
    val id: String,
    val instagram_username: String,
    val last_name: Any,
    val links: SponsorLinks,
    val location: Any,
    val meta: Meta,
    val name: String,
    val numeric_id: Int,
    val photos: List<Photo>,
    val portfolio_url: String,
    val profile_image: ProfileImage,
    val social: Social,
    val tags: Tags,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String,
    val updated_at: String,
    val username: String
)