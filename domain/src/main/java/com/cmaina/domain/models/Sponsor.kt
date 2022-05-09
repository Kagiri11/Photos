package com.cmaina.domain.models

data class Sponsor(
    val acceptedTos: Boolean,
    val bio: String,
    val firstName: String,
    val forHire: Boolean,
    val id: String,
    val instagramUsername: String,
    val lastName: Any,
    val sponsorLinks: SponsorLinks,
    val location: Any,
    val name: String,
    val portfolioUrl: String,
    val profileImage: ProfileImage,
    val sponsorSocial: SponsorSocial,
    val totalCollections: Int,
    val totalLikes: Int,
    val totalPhotos: Int,
    val twitterUsername: String,
    val updatedAt: String,
    val userName: String
)
