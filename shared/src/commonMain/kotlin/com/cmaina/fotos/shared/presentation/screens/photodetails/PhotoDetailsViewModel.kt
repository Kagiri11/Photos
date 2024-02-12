package com.cmaina.fotos.shared.presentation.screens.photodetails

import com.cmaina.domain.models.specificphoto.PreviewPhoto
import com.cmaina.fotos.shared.domain.repositories.AuthRepository
import com.cmaina.fotos.shared.domain.repositories.PhotosRepository
import com.cmaina.fotos.shared.domain.utils.Result.*
import com.cmaina.fotos.shared.presentation.utils.ViewModel
import kotlinx.coroutines.CoroutineScope
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
    private val _userIsAuthenticated = MutableStateFlow<Boolean>(false)
    val userIsAuthenticated : StateFlow<Boolean> get() = _userIsAuthenticated

    private val _messageToUser = MutableStateFlow(false)
    val messageToUser = _messageToUser.asStateFlow()

    init {
        checkIfUserHasBeenAuthenticated()
    }

    fun checkIfPhotoHasBeenLiked(photoId: String) {
        viewModelScope.launch {
            when (val result = photosRepository.getSpecificPhoto(photoId = photoId)) {
                is Success -> {
                    val details = (_detailsUiState.value as PhotoDetailsUiState.Success)
                        .details.copy(photoIsLikedByUser = result.data.likedByUser)
                    _detailsUiState.value = PhotoDetailsUiState.Success(details)
                }

                is Error -> {
                    _detailsUiState.value = PhotoDetailsUiState.Error(result.errorDetails)
                }
            }
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
                is Success -> {
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

                is Error -> {
                    _detailsUiState.value =
                        PhotoDetailsUiState.Error(errorMessage = result.errorDetails)
                }
            }
        }
    }

    private fun checkIfUserHasBeenAuthenticated() = viewModelScope.launch {
        authRepository.checkIfUserHasBeenAuthenticated().collect{
            _userIsAuthenticated.value = it
        }
    }

    fun authenticateUser(authCode: String) = viewModelScope.launch {
        when(authRepository.authenticateUser(authCode = authCode)){
            is Error -> {}
            is Success -> {}
        }
    }
}

data class PhotoLikedState(val photoId: String?, val photoUrl: String?, val blurHash: String?)

fun PreviewPhoto.toPhotoLikedState() =
    PhotoLikedState(photoId = this.id, photoUrl = this.urls?.full, blurHash = this.blurHash)
