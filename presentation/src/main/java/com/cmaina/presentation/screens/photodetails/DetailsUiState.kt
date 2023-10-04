package com.cmaina.presentation.screens.photodetails

data class DetailsUiState(
    val details: Details? = null,
    val isLoading: Boolean,
    val errorMessage: String = ""
)

sealed interface PhotoDetailsUiState{

    data class Success(val details: Details): PhotoDetailsUiState

    object Loading: PhotoDetailsUiState

    data class Error(val errorMessage: String): PhotoDetailsUiState
}

data class Details(
    val userName: String,
    val userPhotoImageUrl: String,
    val numberOfLikes: Int,
    val relatedImages: List<PhotoLikedState>,
    val photoIsLikedByUser: Boolean
)
