package com.cmaina.domain.usecases

import com.cmaina.domain.repository.UsersRepository

class FetchUserPhotosUseCase(private val repository: UsersRepository) {
    suspend operator fun invoke(username: String) = repository.fetchUserPhotos(username)
}
