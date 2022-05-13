package com.cmaina.repository.mappers

import com.cmaina.domain.models.photos.DomainPhotoList
import com.cmaina.network.models.photos.PhotoList

internal fun PhotoList.toDomain() = DomainPhotoList(
    domainPhotoList = photoList.map { it.toDomain() }
)