package com.cmaina.domain.models.photostats

import com.cmaina.network.models.photostats.Historical

data class Likes(
    val historical: Historical,
    val total: Int
)
