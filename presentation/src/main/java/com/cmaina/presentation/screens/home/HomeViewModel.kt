package com.cmaina.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val photosRepository: PhotosRepository
) : ViewModel() {

    private val _homeState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeState: StateFlow<HomeUiState> get() = _homeState

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            when (val result = photosRepository.fetchPhotos()) {
                is Result.Success ->{
                    _homeState.value = HomeUiState.Success(pagedPhotos = result.data)
                }

                is Result.Error -> {
                    _homeState.value = HomeUiState.Error(errorMessage = result.errorDetails)
                }
            }
        }
    }
}
