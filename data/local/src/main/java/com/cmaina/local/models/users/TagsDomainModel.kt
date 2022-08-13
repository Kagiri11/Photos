package com.cmaina.domain.models.users

import com.cmaina.domain.models.specificphoto.TagDomainModel

data class TagsDomainModel(
    val aggregated: List<TagDomainModel>?,
    val custom: List<Custom>?
)
