package com.cmaina.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.specificphoto.SpecificPhotoDomainModel
import com.cmaina.domain.usecases.FetchSpecificPhotoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(
    private val fetchSpecificPhotoUseCase: FetchSpecificPhotoUseCase
) : ViewModel() {

    private val _specificPhoto = MutableLiveData<SpecificPhotoDomainModel>()
    val specificPhoto: LiveData<SpecificPhotoDomainModel> get() = _specificPhoto

    fun fetchPhoto(photoId: String) {
        viewModelScope.launch {
            fetchSpecificPhotoUseCase(photoId = photoId).collect{
                _specificPhoto.value = it
            }
        }
    }
}
