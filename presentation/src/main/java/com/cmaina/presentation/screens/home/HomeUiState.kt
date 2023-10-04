package com.cmaina.presentation.screens.home

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import kotlinx.coroutines.flow.Flow


sealed interface HomeUiState{

    data class Success(val pagedPhotos: Flow<PagingData<DomainPhotoListItem>>): HomeUiState

    object Loading: HomeUiState

    data class Error(val errorMessage: String): HomeUiState
}

