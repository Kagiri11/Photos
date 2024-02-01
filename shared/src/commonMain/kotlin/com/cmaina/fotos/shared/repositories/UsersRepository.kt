package com.cmaina.fotos.shared.repositories

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.Photo
import com.cmaina.domain.models.users.User
import com.cmaina.domain.models.users.portfolio.UserPortFolioDomainModel
import com.cmaina.domain.models.users.statistics.UserStatistics
import com.cmaina.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun fetchUser(username: String): Flow<Result<User>>

    suspend fun fetchUserProfile(): Flow<User>

    suspend fun fetchUserPhotos(username: String): Flow<PagingData<Photo>>

    suspend fun fetchUserPortFolio(): Flow<UserPortFolioDomainModel>

    suspend fun fetchUserStatistics(): Flow<UserStatistics>
}
