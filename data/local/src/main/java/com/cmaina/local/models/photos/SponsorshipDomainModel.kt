package com.cmaina.local.models.photos

data class SponsorshipDomainModel(
    val impressionUrls: List<String>?,
    val domainSponsor: DomainSponsor?,
    val tagline: String?,
    val taglineUrl: String?
)
