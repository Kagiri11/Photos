package com.cmaina.repository.utils

import com.cmaina.network.models.photos.PhotoListItem
import com.cmaina.network.models.specificphoto.SpecificPhoto
import com.cmaina.repository.mappers.toDomain

val PhotoListItem = PhotoListItem(
    alt_description = "",
    blur_hash = "",
    categories = listOf(),
    color = "",
    created_at = null,
    current_user_collections = null,
    description = "cars on the street",
    height = 100,
    id = "qwerty",
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
    id = "qwerty",
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