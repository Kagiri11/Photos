package com.cmaina.domain.repository

import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.models.users.portfolio.UserPortFolioDomainModel
import com.cmaina.domain.models.users.statistics.UserStatistics
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getUser(username: String): Flow<UserDomainModel>

    suspend fun fetchUserProfile(): Flow<UserDomainModel>

    suspend fun fetchUserPhotos(): Flow<DomainPhotoListItem>

    suspend fun fetchUserPortFolio(): Flow<UserPortFolioDomainModel>

    suspend fun fetchUserStatistics(): Flow<UserStatistics>
}
