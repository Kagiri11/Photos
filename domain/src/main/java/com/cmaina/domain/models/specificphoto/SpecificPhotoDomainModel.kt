package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.photos.DomainPhotoLinks
import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.photos.DomainUrls
import com.cmaina.domain.models.photos.SponsorshipDomainModel

data class SpecificPhotoDomainModel(
    val altDescription: Any,
    val blurHash: String,
    val categories: List<Any>,
    val color: String,
    val createdAt: String,
    val currentUserCollections: List<Any>,
    val description: Any,
    val downloads: Int,
    val height: Int,
    val id: String,
    val likedByUser: Boolean,
    val likes: Int,
    val links: DomainPhotoLinks,
    val locationDomainModel: LocationDomainModel,
    val metaDomainModel: MetaDomainModel,
    val relatedCollectionsDomainModel: RelatedCollectionsDomainModel,
    val sponsorshipDomainModel: SponsorshipDomainModel,
    val tags: List<Any>,
    val topics: List<Topic>,
    val urls: DomainUrls,
    val user: DomainPhotoUser,
    val views: Int,
    val width: Int
)
