package com.cmaina.presentation.screens.photodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.specificphoto.PreviewPhoto
import com.cmaina.domain.repository.AuthRepository
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(
    private val photosRepository: PhotosRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _detailsUiState = MutableStateFlow<PhotoDetailsUiState>(PhotoDetailsUiState.Loading)
    val detailsUiState: StateFlow<PhotoDetailsUiState> get() = _detailsUiState

    private val _userIsAuthenticated = MutableStateFlow(false)
    val userIsAuthenticated = _userIsAuthenticated.asStateFlow()

    private val _messageToUser = MutableStateFlow(false)
    val messageToUser = _messageToUser.asStateFlow()

    fun checkIfPhotoHasBeenLiked(photoId: String) {
        viewModelScope.launch {
            when (val result = photosRepository.getSpecificPhoto(photoId = photoId)) {
                is Result.Success -> {
                    val details = (_detailsUiState.value as PhotoDetailsUiState.Success)
                        .details.copy(photoIsLikedByUser = result.data.likedByUser)
                    _detailsUiState.value = PhotoDetailsUiState.Success(details)
                }

                is Result.Error -> {
                    _detailsUiState.value = PhotoDetailsUiState.Error(result.errorDetails)
                }
            }
        }
    }

    private fun checkIfUserHasBeenAuthenticated() = viewModelScope.launch {
        authRepository.checkIfUserHasBeenAuthenticated().collect {
            _userIsAuthenticated.value = it
        }
    }

    fun likePhoto(photoID: String) = viewModelScope.launch {
        photosRepository.likePhoto(photoID)
    }

    fun changeMessageStatus() {
        _messageToUser.value = !_messageToUser.value
    }

    fun fetchPhoto(photoId: String) {
        viewModelScope.launch {
            when (val result = photosRepository.getSpecificPhoto(photoId = photoId)) {
                is Result.Success -> {
                    with(result.data) {


                        val details = Details(
                            userName = user.userName,
                            userPhotoImageUrl = user.userPhotoImageUrl,
                            numberOfLikes = likes,
                            relatedImages = relatedPhotos,
                            photoIsLikedByUser = false
                        )
                        _detailsUiState.value =
                            PhotoDetailsUiState.Success(details = details)
                    }
                }

                is Result.Error -> {
                    _detailsUiState.value =
                        PhotoDetailsUiState.Error(errorMessage = result.errorDetails)
                }
            }
        }
    }

    fun authenticateUser(authCode: String) = viewModelScope.launch {
        when (val result = authRepository.authenticateUser(authCode = authCode)) {
            is Result.Success -> {
                _userIsAuthenticated.value = true
                // save token to persistence
            }

            is Result.Error -> {
                _userIsAuthenticated.value = false
            }
        }
    }
}

data class PhotoLikedState(val photoId: String?, val photoUrl: String?, val blurHash: String?)

fun PreviewPhoto.toPhotoLikedState() =
    PhotoLikedState(photoId = this.id, photoUrl = this.urls?.full, blurHash = this.blurHash)
