package com.cmaina.repository.sources

import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.models.users.portfolio.UserPortFolioDomainModel
import com.cmaina.domain.models.users.statistics.UserStatistics
import com.cmaina.domain.repository.UsersRepository
import com.cmaina.network.api.UsersRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UsersRepositoryImpl(private val usersRemoteSource: UsersRemoteSource): UsersRepository {

    override suspend fun fetchUser(username: String): Flow<UserDomainModel> {
        return when(val result = usersRemoteSource.getUser(username = username)){
            is ApiResponse.Success -> {
                flowOf(result.data.toDomain())
            }
            is ApiResponse.Failure.Error -> flowOf()
            is ApiResponse.Failure.Exception -> flowOf()
        }
    }

    override suspend fun fetchUserProfile(): Flow<UserDomainModel> {
        return flowOf()
    }

    override suspend fun fetchUserPhotos(): Flow<DomainPhotoListItem> {
        return flowOf()
    }

    override suspend fun fetchUserPortFolio(): Flow<UserPortFolioDomainModel> {
        return flowOf()
    }

    override suspend fun fetchUserStatistics(): Flow<UserStatistics> {
        return flowOf()
    }
}