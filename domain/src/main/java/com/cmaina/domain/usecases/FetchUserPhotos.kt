package com.cmaina.domain.usecases

import com.cmaina.domain.repository.UsersRepository

class FetchUserPhotos(private val repository: UsersRepository) {
    suspend operator fun invoke() = repository.fetchUserPhotos()
}
