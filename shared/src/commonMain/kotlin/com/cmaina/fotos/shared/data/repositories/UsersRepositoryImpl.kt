package com.cmaina.fotos.shared.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.Photo
import com.cmaina.domain.models.users.User
import com.cmaina.domain.models.users.portfolio.UserPortFolioDomainModel
import com.cmaina.domain.models.users.statistics.UserStatistics
import com.cmaina.domain.repository.UsersRepository
import com.cmaina.network.api.UsersRemoteSource
import com.cmaina.network.models.users.UserDto
import com.cmaina.fotos.shared.data.mappers.toDomain
import com.cmaina.repository.paging.UserPhotosPagingSource
import com.cmaina.repository.utils.InOut
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UsersRepositoryImpl(
    private val usersRemoteSource: UsersRemoteSource
) : UsersRepository {

    override suspend fun fetchUser(username: String): Flow<Result<User>> {
        val response = usersRemoteSource.getUser(username = username)

        return flowOf(
            InOut<UserDto, User>(response.body())
                .apiCall(response) { it.toDomain() }
        )
    }

    override suspend fun fetchUserProfile(): Flow<User> {
        return flowOf()
    }

    override suspend fun fetchUserPhotos(username: String): Flow<PagingData<Photo>> {
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
