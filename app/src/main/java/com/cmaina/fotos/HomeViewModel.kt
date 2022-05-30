package com.cmaina.fotos

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.usecases.FetchPhotosUseCase
import com.cmaina.domain.usecases.FetchRandomPhotoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val fetchPhotosUseCase: FetchPhotosUseCase,
    private val fetchRandomPhotoUseCase: FetchRandomPhotoUseCase
) : ViewModel() {
    init {
        fetchPhotos()
        fetchRandomPhoto()
    }

    val pics: MutableState<List<DomainPhotoListItem>?> = mutableStateOf(null)
    val randomPhotoId: MutableState<String> = mutableStateOf("not loaded")

    private fun fetchPhotos() = viewModelScope.launch {
        fetchPhotosUseCase().collect { it as PagingData<List<DomainPhotoListItem>>
            Log.d("PhotosCollected", "This is the data collected: ${it.size}")
            pics.value = it
        }
        val somthing = fetchPhotosUseCase() as Flow<PagingData<List<DomainPhotoListItem>>>
        somthing.collectLatest {

        }

    }

    private fun fetchRandomPhoto() = viewModelScope.launch {
        fetchRandomPhotoUseCase().collect {
            randomPhotoId.value = "${it.id}"
        }
    }
}
