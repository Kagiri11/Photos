package com.cmaina.domain.models.users.statistics

import com.cmaina.domain.models.photostats.DomainPhotoStatDownloads

data class UserStatistics(
    val domainPhotoStatDownloads: DomainPhotoStatDownloads?,
    val id: String?,
    val username: String?,
    val views: Views?
)
