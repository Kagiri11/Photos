package com.cmaina.presentation.screens.home

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.Photo
import kotlinx.coroutines.flow.Flow


sealed interface HomeUiState{

    data class Success(val pagedPhotos: Flow<PagingData<Photo>>): HomeUiState

    object Loading: HomeUiState

    data class Error(val errorMessage: String): HomeUiState
}

