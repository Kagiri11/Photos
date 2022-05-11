package com.cmaina.network.models.search

data class PhotoSearchResultDto(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)