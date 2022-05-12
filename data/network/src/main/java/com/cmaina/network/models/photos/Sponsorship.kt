package com.cmaina.network.models.photos

data class Sponsorship(
    val impression_urls: List<String>,
    val sponsor: Sponsor,
    val tagline: String,
    val tagline_url: String
)