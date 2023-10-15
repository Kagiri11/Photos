package com.cmaina.domain.models.search

import com.cmaina.domain.models.photos.Photo

data class PhotoSearchResultDomainModel(
    val searchedPhotoDomainModels: List<Photo>?,
    val total: Int?,
    val totalPages: Int?
)
