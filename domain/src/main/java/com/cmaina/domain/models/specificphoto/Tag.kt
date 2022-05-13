package com.cmaina.domain.models.specificphoto

import com.cmaina.network.models.specificphoto.Source

data class Tag(
    val source: Source,
    val title: String,
    val type: String
)
