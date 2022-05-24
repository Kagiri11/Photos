package com.cmaina.domain.models.users

import com.cmaina.domain.models.photos.DomainSponsorLinks
import com.cmaina.domain.models.photos.DomainUserSocial
import com.cmaina.domain.models.specificphoto.MetaDomainModel

data class UserDomainModel(
    val accepted_tos: Boolean?,
    val allow_messages: Boolean?,
    val badge: Any?,
    val bio: String?,
    val downloads: Int?,
    val first_name: String?,
    val followed_by_user: Boolean?,
    val followers_count: Int?,
    val following_count: Int?,
    val for_hire: Boolean?,
    val id: String?,
    val instagram_username: String?,
    val last_name: Any?,
    val links: DomainSponsorLinks?,
    val location: Any?,
    val metaDomainModel: MetaDomainModel?,
    val name: String?,
    val numeric_id: Int?,
    val userPhotoDomainModels: List<UserPhotoDomainModel>?,
    val portfolio_url: String?,
    val profile_image: ProfileImageDomainModel?,
    val social: DomainUserSocial?,
    val tagsDomainModel: TagsDomainModel?,
    val total_collections: Int?,
    val total_likes: Int?,
    val total_photos: Int?,
    val twitter_username: String?,
    val updated_at: String?,
    val username: String?
)
