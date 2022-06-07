package com.cmaina.fotos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.usecases.FetchPhotosUseCase
import com.cmaina.domain.usecases.FetchRandomPhotoUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
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
    private val randomPhotoId: MutableState<String> = mutableStateOf("not loaded")

    private fun fetchPhotos() {
        viewModelScope.launch {
            fetchPhotosUseCase().collect{
                pics.value = it
            }
        }
    }

    private fun fetchRandomPhoto() = viewModelScope.launch {
        fetchRandomPhotoUseCase().collect {
            randomPhotoId.value = "${it.id}"
        }
    }
}
