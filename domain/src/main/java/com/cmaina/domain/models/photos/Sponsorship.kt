package com.cmaina.domain.models.photos

data class Sponsorship(
    val impressionUrls: List<String>,
    val sponsor: Sponsor,
    val tagline: String,
    val taglineUrl: String
)