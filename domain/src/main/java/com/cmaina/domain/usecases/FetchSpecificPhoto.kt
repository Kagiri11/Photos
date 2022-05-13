package com.cmaina.domain.usecases

import com.cmaina.domain.repository.PhotosRepository

class FetchSpecificPhoto(private val repository: PhotosRepository) {
    suspend operator fun invoke() = repository.getSpecificPhoto()
}
