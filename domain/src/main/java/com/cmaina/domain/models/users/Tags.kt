package com.cmaina.network.models.users

import com.cmaina.network.models.specificphoto.Tag


data class Tags(
    val aggregated: List<Tag>,
    val custom: List<Custom>
)