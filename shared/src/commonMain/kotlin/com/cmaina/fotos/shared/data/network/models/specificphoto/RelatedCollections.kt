package com.cmaina.fotos.shared.data.network.models.specificphoto

data class RelatedCollections(
    val results: List<Result>,
    val total: Int,
    val type: String
)