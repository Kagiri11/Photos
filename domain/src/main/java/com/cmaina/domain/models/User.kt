package com.cmaina.domain.models

data class User(
    val acceptedTos: Boolean,
    val bio: String,
    val firstName: String,
    val forHire: Boolean,
    val id: String,
    val instagramUsername: String,
    val lastName: String,
    val userLinks: UserLinks,
    val location: String,
    val name: String,
    val portfolioUrl: String,
    val userProfileImage: UserProfileImage,
    val userSocial: UserSocial,
    val totalCollections: Int,
    val totalLikes: Int,
    val totalPhotos: Int,
    val twitterUsername: String,
    val updatedAt: String,
    val username: String
)
