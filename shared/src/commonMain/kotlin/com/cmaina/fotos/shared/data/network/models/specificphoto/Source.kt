package com.cmaina.fotos.shared.data.network.models.specificphoto

import com.cmaina.fotos.shared.data.network.models.specificphoto.Ancestry
import com.cmaina.fotos.shared.data.network.models.specificphoto.CoverPhoto

data class Source(
    val ancestry: Ancestry,
    val cover_photo: CoverPhoto,
    val description: String,
    val meta_description: String,
    val meta_title: String,
    val subtitle: String,
    val title: String
)