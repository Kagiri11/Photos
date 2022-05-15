package com.cmaina.fotos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.usecases.FetchPhotosUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val fetchPhotosUseCase: FetchPhotosUseCase) : ViewModel() {

    private fun fetchPhotos() = viewModelScope.launch {
        fetchPhotosUseCase().collect {

        }
    }
}
