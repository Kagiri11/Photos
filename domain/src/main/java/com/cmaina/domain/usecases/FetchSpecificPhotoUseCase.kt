package com.cmaina.domain.usecases

import com.cmaina.domain.repository.PhotosRepository

class FetchSpecificPhotoUseCase(private val repository: PhotosRepository) {
    suspend operator fun invoke() = repository.getSpecificPhoto()
}
