package com.cmaina.repository.utils

import com.cmaina.domain.models.photos.DomainUrls
import com.cmaina.domain.models.photostats.DomainPhotoStatDownloads
import com.cmaina.domain.models.photostats.DomainPhotoStatLikes
import com.cmaina.domain.models.photostats.DomainPhotoStatistics
import com.cmaina.domain.models.photostats.DomainPhotoStatsViews
import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.photos.Urls
import com.cmaina.network.models.photostats.Downloads
import com.cmaina.network.models.photostats.Historical
import com.cmaina.network.models.photostats.Likes
import com.cmaina.network.models.photostats.PhotoStatistics
import com.cmaina.network.models.photostats.Views
import com.cmaina.network.models.specificphoto.SpecificPhoto
import com.cmaina.repository.mappers.toDomain
const val total = 1
const val id = "1234"

val PhotoListItem = PhotoListItem(
    alt_description = "",
    blur_hash = "",
    categories = listOf(),
    color = "",
    created_at = null,
    current_user_collections = null,
    description = "cars on the street",
    height = 100,
    id = id,
    liked_by_user = false,
    likes = 123,
    links = null,
    promoted_at = null,
    sponsorship = null,
    updated_at = null,
    urls = null,
    user = null,
    width = 100,
    topic_submissions = null,
    exif = null,
    location = null,
    views = null,
    downloads = 12
)

val DomainPhotoListItem = PhotoListItem.toDomain()

val SpecificPhoto = SpecificPhoto(
    alt_description = "",
    blur_hash = "",
    categories = listOf(),
    color = "",
    created_at = null,
    current_user_collections = null,
    description = "cars on the street",
    height = 100,
    id = id,
    liked_by_user = false,
    likes = 123,
    links = null,
    promoted_at = null,
    sponsorship = null,
    updated_at = null,
    urls = null,
    user = null,
    width = 100,
    topic_submissions = null,
    exif = null,
    location = null,
    views = null,
    downloads = 12,
    meta = null,
    public_domain = false,
    related_collections = null,
    tags = listOf(),
    tags_preview = listOf(),
    topics = listOf()
)

val SpecificDomainPhoto = SpecificPhoto.toDomain()

val historical = Historical(change = 1, quantity = 12, resolution = "HD", values = listOf())
