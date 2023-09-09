package com.cmaina.presentation.screens.home

import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem

data class HomeUiState(
    val pagedPhotos: PagingData<DomainPhotoListItem>,
    val loading: Boolean,
    val error: String
)
