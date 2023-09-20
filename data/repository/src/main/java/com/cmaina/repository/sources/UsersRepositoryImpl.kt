package com.cmaina.repository.sources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.models.users.portfolio.UserPortFolioDomainModel
import com.cmaina.domain.models.users.statistics.UserStatistics
import com.cmaina.domain.repository.UsersRepository
import com.cmaina.domain.utils.Result
import com.cmaina.network.api.UsersNetworkSource
import com.cmaina.network.api.UsersRemoteSource
import com.cmaina.network.models.users.UserDto
import com.cmaina.repository.mappers.toDomain
import com.cmaina.repository.paging.UserPhotosPagingSource
import com.cmaina.repository.utils.InOut
import com.cmaina.repository.utils.flowSafeApiCall
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UsersRepositoryImpl(
    private val usersRemoteSource: UsersRemoteSource
) : UsersRepository {

    override suspend fun fetchUser(username: String): Flow<Result<UserDomainModel>> {
        val response = usersRemoteSource.getUser(username = username)

        return flowOf(
            InOut<UserDto, UserDomainModel>(response.body())
                .apiCall(response) { it.toDomain() }
        )
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
