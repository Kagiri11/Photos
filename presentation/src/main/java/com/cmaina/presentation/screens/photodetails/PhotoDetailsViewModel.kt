package com.cmaina.presentation.screens.photodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.specificphoto.PreviewPhotoDomainModel
import com.cmaina.domain.repository.AuthRepository
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(
    private val photosRepository: PhotosRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _photoUrlLink = MutableLiveData<String>()
    val photoUrlLink: LiveData<String> get() = _photoUrlLink

    private val _blurHashCode = MutableLiveData<String>()
    val blurHashCode: LiveData<String> get() = _blurHashCode

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _userPhotoUrl = MutableLiveData<String>()
    val userPhotoUrl: LiveData<String> get() = _userPhotoUrl

    private val _numberOfLikes = MutableLiveData<Int>()
    val numberOfLikes: LiveData<Int> get() = _numberOfLikes

    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> get() = _isUserAuthenticated

    private val _relatedPhotos = MutableLiveData<List<PreviewPhotoDomainModel>>()
    val relatedPhotos: LiveData<List<PreviewPhotoDomainModel>> get() = _relatedPhotos

    private val _relatedPhotosStrings = MutableLiveData<List<PhotoLikedState>>()
    val relatedPhotosStrings: LiveData<List<PhotoLikedState>> get() = _relatedPhotosStrings

    private val _photoLikedByUser = MutableLiveData<Boolean>()
    val photoLikedByUser: LiveData<Boolean> get() = _photoLikedByUser

    fun checkIfPhotoIsLiked(photoId: String) {
        viewModelScope.launch {
            when (val result = photosRepository.getSpecificPhoto(photoId = photoId)) {
                is NetworkResult.Success -> {
                    _photoLikedByUser.value = result.data.likedByUser
                }
                is NetworkResult.Error -> {
                }
            }
        }
    }

    fun fetchPhoto(photoId: String) {
        viewModelScope.launch {
            when (val result = photosRepository.getSpecificPhoto(photoId = photoId)) {
                is NetworkResult.Success -> {
                    _photoUrlLink.value = result.data.urls?.raw
                    _username.value = result.data.user?.username
                    _numberOfLikes.value = result.data.likes
                    _userPhotoUrl.value = result.data.user?.domainUserProfileImage?.large
                    _blurHashCode.value = result.data.blurHash

                    result.data.relatedCollectionsDomainModel?.collectionDomainModels?.map { collectionDomainModel ->
                        _relatedPhotos.value = collectionDomainModel.previewPhotoDomainModels
                    }

                    val strings = relatedPhotos.value?.map {
                        it.toPhotoLikedState()
                    }?.toMutableList()
                    strings?.add(
                        PhotoLikedState(
                            photoId = photoId,
                            result.data.urls?.raw,
                            blurHash = result.data.blurHash
                        )

                    )
                    val newPhotos = strings?.sortedWith(
                        compareBy {
                            it.photoUrl == _photoUrlLink.value
                        }
                    )?.reversed()
                    _relatedPhotosStrings.value = newPhotos
                }
                is NetworkResult.Error -> {}
            }
        }
    }

    fun authenticateUser(authCode: String) = viewModelScope.launch {
        when (val result = authRepository.authenticateUser(authCode = authCode)) {
            is NetworkResult.Success -> {
                _isUserAuthenticated.value = true
                // save token to persistence
            }
            is NetworkResult.Error -> {
                _isUserAuthenticated.value = false
            }
        }
    }
}

data class PhotoLikedState(val photoId: String?, val photoUrl: String?, val blurHash: String?)

fun PreviewPhotoDomainModel.toPhotoLikedState() =
    PhotoLikedState(photoId = this.id, photoUrl = this.urls?.full, blurHash = this.blur_hash)
