package com.cmaina.tacc.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.DomainPhoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    private val _photosOfMars = MutableStateFlow<List<DomainPhoto>>(emptyList())
    val photosOfMars: StateFlow<List<DomainPhoto>> get() = _photosOfMars

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            photosRepository.fetchMarsPhotosFromLocalSource().catch {
                emit(
                    emptyList()
                )
            }.collect {
                _photosOfMars.value = it
            }
        }
    }
}
