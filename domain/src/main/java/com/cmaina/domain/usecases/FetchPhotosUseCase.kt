package com.cmaina.domain.usecases

import com.cmaina.domain.repository.PhotosRepository

class FetchPhotosUseCase(private val repository: PhotosRepository) {
    suspend operator fun invoke() = repository.fetchPhotos()
}