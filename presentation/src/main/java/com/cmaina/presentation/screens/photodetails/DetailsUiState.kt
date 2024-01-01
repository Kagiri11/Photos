package com.cmaina.presentation.screens.photodetails

sealed interface PhotoDetailsUiState{

    data class Success(val details: Details): PhotoDetailsUiState

    object Loading: PhotoDetailsUiState

    data class Error(val errorMessage: String): PhotoDetailsUiState
}

data class Details(
    val userName: String,
    val userPhotoImageUrl: String,
    val numberOfLikes: Int,
    val relatedImages: List<Pair<String, String>>,
    val photoIsLikedByUser: Boolean
)
