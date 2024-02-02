package com.cmaina.fotos.shared.data.repositories


import com.cmaina.network.models.users.UserDto
import com.cmaina.fotos.shared.data.mappers.toDomain
import com.cmaina.fotos.shared.data.network.InOut
import com.cmaina.fotos.shared.data.network.UsersRemoteSource
import com.cmaina.fotos.shared.domain.models.users.User
import com.cmaina.fotos.shared.domain.models.users.portfolio.UserPortFolioDomainModel
import com.cmaina.fotos.shared.domain.models.users.statistics.UserStatistics
import com.cmaina.fotos.shared.domain.repositories.UsersRepository
import com.cmaina.fotos.shared.domain.utils.Result
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

    override suspend fun fetchUserPortFolio(): Flow<UserPortFolioDomainModel> {
        return flowOf()
    }

    override suspend fun fetchUserStatistics(): Flow<UserStatistics> {
        return flowOf()
    }
}
