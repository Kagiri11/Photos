package com.cmaina.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val photosRepository: PhotosRepository
) : ViewModel() {
    private val _searchedPhotos = MutableStateFlow<List<DomainPhotoListItem>?>(null)
    val searchedPhotos = _searchedPhotos.asStateFlow()

    fun searchPhotos(searchQuery: String) {
        viewModelScope.launch {
            photosRepository.searchPhoto(searchString = searchQuery).collect { result ->
                _searchedPhotos.value = result
            }
        }
    }

    fun returnNullPhotos(){
        viewModelScope.launch {
            _searchedPhotos.value = null
        }
    }
}
