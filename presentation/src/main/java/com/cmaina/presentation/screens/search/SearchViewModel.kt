package com.cmaina.presentation.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.Photo
import com.cmaina.domain.repository.PhotosRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class SearchViewModel(
    private val photosRepository: PhotosRepository
) : ViewModel() {

    val searchString = MutableStateFlow("")
    private val _searchedPhotos = MutableLiveData<Flow<PagingData<Photo>>>(null)
    val searchedPhotos: LiveData<Flow<PagingData<Photo>>> = _searchedPhotos

    init {
        searchPhotos()
    }

    @OptIn(FlowPreview::class)
    fun searchPhotos() {
        viewModelScope.launch {
            searchString
                .debounce(1000)
                .distinctUntilChanged()
                .collect {
                    if (it.isNotEmpty()) {
                        photosRepository.searchPhoto(searchString = it).let { result ->
                            delay(50)
                            _searchedPhotos.value = result
                        }
                    } else {
                        returnNullPhotos()
                    }
                }
        }
    }

    private fun returnNullPhotos() {
        viewModelScope.launch {
            _searchedPhotos.value = null
        }
    }
}
