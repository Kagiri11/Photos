package com.cmaina.domain.models.users.statistics

import com.cmaina.fotos.shared.domain.models.users.statistics.UserHistorical

data class Views(
    val userHistorical: com.cmaina.fotos.shared.domain.models.users.statistics.UserHistorical?,
    val total: Int?
)
