package com.cmaina.domain.usecases

import com.cmaina.domain.repository.UsersRepository

class FetchUserUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(username: String) = usersRepository.fetchUser(username)
}
