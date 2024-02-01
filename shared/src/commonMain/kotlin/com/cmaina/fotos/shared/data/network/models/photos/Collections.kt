package com.cmaina.fotos.shared.data.network.models.photos

data class Collections(
    val results: List<Result>,
    val total: Int,
    val type: String
)