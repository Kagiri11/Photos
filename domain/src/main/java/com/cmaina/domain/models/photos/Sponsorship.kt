package com.cmaina.domain.models.photos

data class Sponsorship(
    val impressionUrls: List<String>,
    val domainSponsor: DomainSponsor,
    val tagline: String,
    val taglineUrl: String
)