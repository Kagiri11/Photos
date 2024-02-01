package com.cmaina.fotos.shared.data.network.models.photos

import com.cmaina.fotos.shared.data.network.models.photos.Sponsor
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