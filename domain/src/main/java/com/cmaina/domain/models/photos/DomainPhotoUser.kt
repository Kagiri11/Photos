package com.cmaina.domain.models.photos

data class DomainPhotoUser(
    val acceptedTos: Boolean,
    val bio: String,
    val firstName: String,
    val forHire: Boolean,
    val id: String,
    val instagramUsername: String,
    val lastName: String,
    val domainUserLinks: DomainUserLinks,
    val location: String,
    val name: String,
    val portfolioUrl: String,
    val domainUserProfileImage: DomainUserProfileImage,
    val domainUserSocial: DomainUserSocial,
    val totalCollections: Int,
    val totalLikes: Int,
    val totalPhotos: Int,
    val twitterUsername: String,
    val updatedAt: String,
    val username: String
)
