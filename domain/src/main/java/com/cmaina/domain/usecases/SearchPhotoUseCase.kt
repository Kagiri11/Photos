package com.cmaina.domain.usecases

import com.cmaina.domain.repository.PhotosRepository

class SearchPhotoUseCase(private val repository: PhotosRepository) {
    suspend operator fun invoke(searchString: String) = repository.searchPhoto(searchString = searchString)
}
