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

    private val _homeUiState = MutableStateFlow(HomeUiState(isLoading = true))
    val homeUiState: StateFlow<HomeUiState> get() = _homeUiState

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            when (val result = photosRepository.fetchPhotos()) {
                is com.cmaina.domain.utils.NetworkResult.Result.Success -> _homeUiState.value = HomeUiState(
                    pagedPhotos = result.data,
                    isLoading = false
                )


                is com.cmaina.domain.utils.NetworkResult.Result.Error -> _homeUiState.value =
                    HomeUiState(errorMessage = result.errorDetails, isLoading = false)
            }
        }
    }
}
