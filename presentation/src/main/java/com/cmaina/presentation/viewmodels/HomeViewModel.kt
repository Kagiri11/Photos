package com.cmaina.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.usecases.FetchPhotosUseCase
import com.cmaina.domain.usecases.FetchRandomPhotoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchPhotosUseCase: FetchPhotosUseCase,
    private val fetchRandomPhotoUseCase: FetchRandomPhotoUseCase
) : ViewModel() {
    init {
        fetchPhotos()
        fetchRandomPhoto()
    }

    private val _pics = MutableLiveData<Flow<PagingData<DomainPhotoListItem>>>(null)
    val pics : LiveData<Flow<PagingData<DomainPhotoListItem>>> get() = _pics
    val randomPhoto: MutableState<DomainPhotoListItem?> = mutableStateOf(null)

    private fun fetchPhotos() {
        viewModelScope.launch {
            fetchPhotosUseCase().let {
                delay(200)
                _pics.value = it
            }
        }
    }

    private fun fetchRandomPhoto() = viewModelScope.launch {
        fetchRandomPhotoUseCase().collect {
            randomPhoto.value = it
        }
    }
}
