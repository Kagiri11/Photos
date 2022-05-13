package com.cmaina.domain.usecases

import com.cmaina.domain.repository.UsersRepository

class FetchUserPortFolioUseCase(private val repository: UsersRepository) {
    suspend operator fun invoke() = repository.fetchUserPortFolio()
}
