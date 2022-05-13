package com.cmaina.domain.usecases

import com.cmaina.domain.repository.UsersRepository

class FetchUserProfile(private val repository: UsersRepository) {
    suspend operator fun invoke() = repository.fetchUserProfile()
}
