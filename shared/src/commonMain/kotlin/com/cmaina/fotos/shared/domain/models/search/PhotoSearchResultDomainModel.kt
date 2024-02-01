package com.cmaina.fotos.shared.domain.models.search

import com.cmaina.fotos.shared.domain.models.photos.Photo

data class PhotoSearchResultDomainModel(
    val searchedPhotoDomainModels: List<com.cmaina.fotos.shared.domain.models.photos.Photo>?,
    val total: Int?,
    val totalPages: Int?
)
