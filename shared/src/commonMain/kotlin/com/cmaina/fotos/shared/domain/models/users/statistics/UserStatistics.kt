package com.cmaina.fotos.shared.domain.models.users.statistics

import com.cmaina.domain.models.users.statistics.Views
import com.cmaina.fotos.shared.domain.models.photostats.DomainPhotoStatDownloads

data class UserStatistics(
    val domainPhotoStatDownloads: com.cmaina.fotos.shared.domain.models.photostats.DomainPhotoStatDownloads?,
    val id: String?,
    val username: String?,
    val views: Views?
)
