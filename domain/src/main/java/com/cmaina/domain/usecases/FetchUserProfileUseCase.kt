package com.cmaina.domain.usecases

import com.cmaina.domain.repository.UsersRepository

class FetchUserProfileUseCase(private val repository: UsersRepository) {
    suspend operator fun invoke() = repository.fetchUserProfile()
}
