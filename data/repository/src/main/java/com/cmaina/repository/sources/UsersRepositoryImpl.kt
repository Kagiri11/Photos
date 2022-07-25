package com.cmaina.repository.sources

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.models.users.portfolio.UserPortFolioDomainModel
import com.cmaina.domain.models.users.statistics.UserStatistics
import com.cmaina.domain.repository.UsersRepository
import com.cmaina.network.api.UsersRemoteSource
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.paging.PhotosPagingSource
import com.cmaina.repository.paging.UserPhotosPagingSource
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UsersRepositoryImpl(private val usersRemoteSource: UsersRemoteSource) : UsersRepository {

    override suspend fun fetchUser(username: String): Flow<UserDomainModel> {
        return when (val result = usersRemoteSource.getUser(username = username)) {
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

    override suspend fun fetchUserPhotos(username: String): Flow<PagingData<DomainPhotoListItem>> {
        val pagingConfig = PagingConfig(pageSize = 30)
        val userPhotosPager = Pager(pagingConfig) {
            UserPhotosPagingSource(usersRemoteSource = usersRemoteSource, username = username)
        }.flow
        return userPhotosPager
    }

    override suspend fun fetchUserPortFolio(): Flow<UserPortFolioDomainModel> {
        return flowOf()
    }

    override suspend fun fetchUserStatistics(): Flow<UserStatistics> {
        return flowOf()
    }
}
