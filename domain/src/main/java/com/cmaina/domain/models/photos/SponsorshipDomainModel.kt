package com.cmaina.domain.models.photos

data class SponsorshipDomainModel(
    val impressionUrls: List<String>?,
    val domainSponsor: DomainSponsor?,
    val tagline: String?,
    val taglineUrl: String?
)