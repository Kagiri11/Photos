package com.cmaina.domain.models.photos

data class DomainSponsor(
    val acceptedTos: Boolean?,
    val bio: String?,
    val firstName: String?,
    val forHire: Boolean?,
    val id: String?,
    val instagramUsername: String?,
    val lastName: String?,
    val domainSponsorLinks: DomainSponsorLinks?,
    val location: Any?,
    val name: String?,
    val portfolioUrl: String?,
    val domainProfileImage: DomainProfileImage?,
    val domainSponsorSocial: DomainSponsorSocial?,
    val totalCollections: Int?,
    val totalLikes: Int?,
    val totalPhotos: Int?,
    val twitterUsername: String?,
    val updatedAt: String?,
    val userName: String?
)
