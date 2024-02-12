package com.cmaina.fotos.shared.presentation.screens.home


import com.cmaina.fotos.shared.domain.repositories.PhotosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class HomeViewModel(
    private val photosRepository: PhotosRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> get() = _uiState

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            _uiState.value = when (val result = photosRepository.fetchPhotos()) {
                is com.cmaina.fotos.shared.domain.utils.Result.Success -> HomeUiState.Success(pagedPhotos = result.data)
                is com.cmaina.fotos.shared.domain.utils.Result.Error -> HomeUiState.Error(errorMessage = result.errorDetails)
            }
        }
    }
}
