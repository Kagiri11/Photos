package com.cmaina.network.models.search

import com.cmaina.network.models.specificphoto.Subcategory

data class Ancestry(
    val category: Category,
    val subcategory: Subcategory,
    val type: Type
)
