package com.cmaina.network.models.users.statistics

data class UserHistorical(
    val average: Int,
    val change: Int,
    val quantity: Int,
    val resolution: String,
    val values: List<Value>
)