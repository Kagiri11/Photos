package com.cmaina.fotos.shared.domain.models.photostats

data class DomainPhotoStatHistorical(
    val change: Int?,
    val quantity: Int?,
    val resolution: String?,
    val domainPhotoStatsValues: List<com.cmaina.fotos.shared.domain.models.photostats.DomainPhotoStatsValue>?
)