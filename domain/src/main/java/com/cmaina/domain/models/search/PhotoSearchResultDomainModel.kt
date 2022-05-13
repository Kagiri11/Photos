package com.cmaina.domain.models.search

data class PhotoSearchResultDomainModel(
    val searchedPhotoDomainModels: List<SearchedPhotoDomainModel>,
    val total: Int,
    val total_pages: Int
)
