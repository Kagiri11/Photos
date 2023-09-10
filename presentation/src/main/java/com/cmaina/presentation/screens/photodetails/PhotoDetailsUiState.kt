package com.cmaina.presentation.screens.photodetails

data class PhotoDetailsUiState(
    val details: Details? = null,
    val isLoading: Boolean,
    val errorMessage: String
)

data class Details(
    val userName: String,
    val userPhotoImageUrl: String,
    val numberOfLikes: Int,
    val relatedImages: List<PhotoLikedState>,
    val photoIsLikedByUser: Boolean
)
