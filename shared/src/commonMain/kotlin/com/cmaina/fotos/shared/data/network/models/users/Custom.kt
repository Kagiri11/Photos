package com.cmaina.network.models.users

import com.cmaina.fotos.shared.data.network.models.specificphoto.Source

data class Custom(
    val source: Source,
    val title: String,
    val type: String
)