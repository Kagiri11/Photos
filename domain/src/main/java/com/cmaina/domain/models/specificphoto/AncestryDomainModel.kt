package com.cmaina.domain.models.specificphoto

data class AncestryDomainModel(
    val categoryDomainModel: CategoryDomainModel,
    val subcategory: Subcategory,
    val type: Type
)
