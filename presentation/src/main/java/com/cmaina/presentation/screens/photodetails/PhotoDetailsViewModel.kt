package com.cmaina.presentation.screens.photodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.specificphoto.PreviewPhotoDomainModel
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

    private val _detailsUiState = MutableStateFlow(DetailsUiState(isLoading = true))
    val detailsUiState: StateFlow<DetailsUiState> get() = _detailsUiState

    private val _userIsAuthenticated = MutableStateFlow(false)
    val userIsAuthenticated = _userIsAuthenticated.asStateFlow()

    private val _messageToUser = MutableStateFlow(false)
    val messageToUser = _messageToUser.asStateFlow()

    fun checkIfPhotoIsLiked(photoId: String) {
        viewModelScope.launch {
            when (val result = photosRepository.getSpecificPhoto(photoId = photoId)) {
                is Result.Success -> {
                    val details =
                        _detailsUiState.value.details?.copy(photoIsLikedByUser = result.data.likedByUser)
                    _detailsUiState.value = _detailsUiState.value.copy(details = details)
                }

                is Result.Error -> {
                }
            }
        }
    }

    private fun checkIfUserIsAuthenticated() = viewModelScope.launch {
        authRepository.checkIfUserHasBeenAuthenticated().collect {
            _userIsAuthenticated.value = it
        }
    }

    fun likePhoto(photoID: String) = viewModelScope.launch {
        checkIfUserIsAuthenticated()
        if (_userIsAuthenticated.value) {
            photosRepository.likePhoto(photoID)
        } else {
            changeMessageStatus()
        }
    }

    fun changeMessageStatus() {
        _messageToUser.value = !_messageToUser.value
    }

    fun fetchPhoto(photoId: String) {
        viewModelScope.launch {
            when (val result = photosRepository.getSpecificPhoto(photoId = photoId)) {
                is Result.Success -> {

                    with(result.data) {
                        val strings =
                            relatedCollectionsDomainModel?.collectionDomainModels?.first()?.previewPhotoDomainModels?.map {
                                it.toPhotoLikedState()
                            }?.toMutableList()

                        strings?.add(
                            PhotoLikedState(
                                photoId = photoId,
                                photoUrl = urls?.raw,
                                blurHash = blurHash
                            )

                        )
                        val details = Details(
                            userName = user?.username ?: "",
                            userPhotoImageUrl = user?.domainUserProfileImage?.large
                                ?: "",
                            numberOfLikes = likes ?: 0,
                            relatedImages = strings ?: listOf(),
                            photoIsLikedByUser = false
                        )
                        _detailsUiState.value =
                            DetailsUiState(details = details, isLoading = false)
                    }
                }

                is Result.Error -> {
                    _detailsUiState.value =
                        DetailsUiState(errorMessage = result.errorDetails, isLoading = false)
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

fun PreviewPhotoDomainModel.toPhotoLikedState() =
    PhotoLikedState(photoId = this.id, photoUrl = this.urls?.full, blurHash = this.blur_hash)
