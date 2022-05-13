package com.cmaina.domain.usecases

import com.cmaina.domain.repository.PhotosRepository

class FetchRandomPhotoUseCase(private val repository: PhotosRepository) {
    suspend operator fun invoke() = repository.getRandomPhoto()
}
