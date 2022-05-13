package com.cmaina.network.models.search

import com.cmaina.domain.models.search.SearchCategoryDomainModel

data class Ancestry(
    val searchCategoryDomainModel: SearchCategoryDomainModel,
    val subcategory: Subcategory,
    val type: Type
)