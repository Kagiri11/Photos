package com.cmaina.network.models.users.statistics

import com.cmaina.network.models.photostats.Downloads

data class UserStatistics(
    val downloads: Downloads,
    val id: String,
    val username: String,
    val views: Views
)