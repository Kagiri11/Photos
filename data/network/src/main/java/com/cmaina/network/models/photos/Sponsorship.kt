package com.cmaina.network.models.photos

import com.google.gson.annotations.SerializedName

data class Sponsorship(
    @SerializedName("impression_urls")
    val impression_urls: List<String>?,
    @SerializedName("sponsor")
    val sponsor: Sponsor?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("tagline_url")
    val tagline_url: String?
)