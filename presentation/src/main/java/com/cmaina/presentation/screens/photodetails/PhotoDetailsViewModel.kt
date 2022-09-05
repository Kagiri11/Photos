package com.cmaina.presentation.screens.photodetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.specificphoto.PreviewPhotoDomainModel
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(
    private val photosRepository: PhotosRepository
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

    private val _relatedPhotos = MutableLiveData<List<PreviewPhotoDomainModel>>()
    val relatedPhotos: LiveData<List<PreviewPhotoDomainModel>> get() = _relatedPhotos

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
                }
                is NetworkResult.Error -> {}
            }
        }
    }
}
