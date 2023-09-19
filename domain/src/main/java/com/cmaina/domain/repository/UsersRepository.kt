package com.cmaina.domain.repository

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.models.users.portfolio.UserPortFolioDomainModel
import com.cmaina.domain.models.users.statistics.UserStatistics
import com.cmaina.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun fetchUser(username: String): Flow<Result<UserDomainModel>>

    suspend fun fetchUserProfile(): Flow<UserDomainModel>

    suspend fun fetchUserPhotos(username: String): Flow<PagingData<DomainPhotoListItem>>

    suspend fun fetchUserPortFolio(): Flow<UserPortFolioDomainModel>

    suspend fun fetchUserStatistics(): Flow<UserStatistics>
}
