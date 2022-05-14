package com.cmaina.domain.models.specificphoto

import com.cmaina.domain.models.photos.DomainPhotoLinks
import com.cmaina.domain.models.photos.DomainPhotoUser
import com.cmaina.domain.models.photos.DomainUrls
import com.cmaina.domain.models.photos.SponsorshipDomainModel
import com.cmaina.domain.models.search.TopicSubmissions

data class SpecificPhoto(
    val alt_description: Any,
    val blur_hash: String,
    val categories: List<Any>,
    val color: String,
    val created_at: String,
    val current_user_collections: List<Any>,
    val description: Any,
    val downloads: Int,
    val exif: Exif,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: DomainPhotoLinks,
    val locationDomainModel: LocationDomainModel,
    val metaDomainModel: MetaDomainModel,
    val promoted_at: Any,
    val public_domain: Boolean,
    val related_collectionsDomainModel: RelatedCollectionsDomainModel,
    val sponsorshipDomainModel: SponsorshipDomainModel,
    val tags: List<Any>,
    val tags_preview: List<Any>,
    val topic_submissions: TopicSubmissions,
    val topics: List<Topic>,
    val updated_at: String,
    val urls: DomainUrls,
    val user: DomainPhotoUser,
    val views: Int,
    val width: Int
)
