package com.cmaina.fotos.shared.domain.repositories

import com.cmaina.fotos.shared.domain.models.users.User
import com.cmaina.fotos.shared.domain.models.users.portfolio.UserPortFolioDomainModel
import com.cmaina.fotos.shared.domain.models.users.statistics.UserStatistics
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun fetchUser(username: String): Flow<Result<com.cmaina.fotos.shared.domain.models.users.User>>

    suspend fun fetchUserProfile(): Flow<com.cmaina.fotos.shared.domain.models.users.User>


    suspend fun fetchUserPortFolio(): Flow<com.cmaina.fotos.shared.domain.models.users.portfolio.UserPortFolioDomainModel>

    suspend fun fetchUserStatistics(): Flow<com.cmaina.fotos.shared.domain.models.users.statistics.UserStatistics>
}
