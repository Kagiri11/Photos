package com.cmaina.fotos.shared.models.search

import com.cmaina.fotos.shared.models.photos.Photo

data class PhotoSearchResultDomainModel(
    val searchedPhotoDomainModels: List<Photo>?,
    val total: Int?,
    val totalPages: Int?
)
