package com.cmaina.presentation.screens.photodetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.specificphoto.PreviewPhotoDomainModel
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.usecases.FetchSpecificPhotoUseCase
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(
    private val fetchSpecificPhotoUseCase: FetchSpecificPhotoUseCase
) : ViewModel() {

    private val _specificPhoto = MutableLiveData<SpecificPhotoDomainModel>()
    val specificPhoto: LiveData<SpecificPhotoDomainModel> get() = _specificPhoto

    private val _relatedPhotos = MutableLiveData<List<PreviewPhotoDomainModel>>()
    val relatedPhotos: LiveData<List<PreviewPhotoDomainModel>> get() = _relatedPhotos

    fun fetchPhoto(photoId: String) {
        viewModelScope.launch {
            fetchSpecificPhotoUseCase(photoId = photoId).collect {
                Log.d("SpecificPhotoImage", "Specific photo in vm: $it")
                _specificPhoto.value = it

                it.relatedCollectionsDomainModel?.collectionDomainModels?.map { collectionDomainModel ->
                   _relatedPhotos.value = collectionDomainModel.previewPhotoDomainModels
                }
            }
        }
    }
}
