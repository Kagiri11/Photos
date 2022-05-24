package com.cmaina.domain.models.specificphoto

data class Source(
    val ancestryDomainModel: AncestryDomainModel??,
    val cover_photoDomainModel: CoverPhotoDomainModel??,
    val description: String?,
    val meta_description: String?,
    val meta_title: String?,
    val subtitle: String?,
    val title: String?
)
