package com.cmaina.domain.models.search

import com.cmaina.domain.models.photos.DomainPhotoListItem

data class PhotoSearchResultDomainModel(
    val searchedPhotoDomainModels: List<DomainPhotoListItem>?,
    val total: Int?,
    val totalPages: Int?
)
