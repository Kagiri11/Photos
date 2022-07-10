package com.cmaina.domain.models.users

import com.cmaina.domain.models.specificphoto.Source

data class Custom(
    val source: Source?,
    val title: String?,
    val type: String?
)