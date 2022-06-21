package com.cmaina.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.usecases.FetchPhotosUseCase
import com.cmaina.domain.usecases.FetchRandomPhotoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchPhotosUseCase: FetchPhotosUseCase,
    private val fetchRandomPhotoUseCase: FetchRandomPhotoUseCase
) : ViewModel() {
    init {
        fetchPhotos()
        fetchRandomPhoto()
    }

    val pics: MutableStateFlow<List<DomainPhotoListItem>> = MutableStateFlow(listOf())
    val randomPhoto: MutableState<DomainPhotoListItem?> = mutableStateOf(null)

    private fun fetchPhotos() {
        viewModelScope.launch {
            fetchPhotosUseCase().collect {
                pics.value = it
            }
        }
    }

    private fun fetchRandomPhoto() = viewModelScope.launch {
        fetchRandomPhotoUseCase().collect {
            randomPhoto.value = it
        }
    }
}
