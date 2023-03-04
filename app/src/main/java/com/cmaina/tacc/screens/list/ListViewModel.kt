package com.cmaina.tacc.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    private val _marsPhotos = MutableStateFlow<List<DomainPhoto>>(emptyList())
    val marsPhotos: StateFlow<List<DomainPhoto>> get() = _marsPhotos

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            photosRepository.fetchMarsPhotos().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        _marsPhotos.value = it.data
                    }
                    is NetworkResult.Error -> {
                    }
                }
            }
        }
    }
}
