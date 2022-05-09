package com.cmaina.domain.models

import com.cmaina.domain.models.Sponsor

data class Sponsorship(
    val impressionUrls: List<String>,
    val sponsor: Sponsor,
    val tagline: String,
    val taglineUrl: String
)