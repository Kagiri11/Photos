package com.cmaina.domain.models.specificphoto

data class RelatedCollectionsDomainModel(
    val collectionDomainModels: List<CollectionDomainModel>,
    val total: Int,
    val type: String
)
