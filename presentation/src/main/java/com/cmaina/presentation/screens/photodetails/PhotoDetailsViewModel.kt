package com.cmaina.presentation.screens.photodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.specificphoto.PreviewPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(
    private val photosRepository: PhotosRepository
) : ViewModel() {

    private val _photoUrlLink = MutableLiveData<String>()
    val photoUrlLink: LiveData<String> get() = _photoUrlLink

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> get() = _username

    private val _userPhotoUrl = MutableLiveData<String>()
    val userPhotoUrl: LiveData<String> get() = _userPhotoUrl

    private val _numberOfLikes = MutableLiveData<Int>()
    val numberOfLikes: LiveData<Int> get() = _numberOfLikes

    private val _relatedPhotos = MutableLiveData<List<PreviewPhotoDomainModel>>()
    val relatedPhotos: LiveData<List<PreviewPhotoDomainModel>> get() = _relatedPhotos

    fun fetchPhoto(photoId: String) {
        viewModelScope.launch {
            photosRepository.getSpecificPhoto(photoId = photoId).collect { photo ->
                _photoUrlLink.value = photo.urls?.raw
                _username.value = photo.user?.username
                _numberOfLikes.value = photo.likes
                _userPhotoUrl.value = photo.user?.domainUserProfileImage?.large

                photo.relatedCollectionsDomainModel?.collectionDomainModels?.map { collectionDomainModel ->
                    _relatedPhotos.value = collectionDomainModel.previewPhotoDomainModels
                }
            }
        }
    }
}
