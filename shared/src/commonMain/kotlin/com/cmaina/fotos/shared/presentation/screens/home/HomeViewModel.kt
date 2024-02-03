package com.cmaina.fotos.shared.presentation.screens.home


import com.cmaina.fotos.shared.domain.repositories.PhotosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel(
    private val photosRepository: PhotosRepository,
    private val viewModelScope: CoroutineScope
) : KoinComponent {

    private val _homeState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeState: StateFlow<HomeUiState> get() = _homeState

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            _homeState.value = when (val result = photosRepository.fetchPhotos()) {
                is com.cmaina.fotos.shared.domain.utils.Result.Success -> HomeUiState.Success(pagedPhotos = result.data)
                is com.cmaina.fotos.shared.domain.utils.Result.Error -> HomeUiState.Error(errorMessage = result.errorDetails)
            }
        }
    }
}