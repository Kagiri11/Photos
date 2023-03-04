package com.cmaina.tacc.screens.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.PhotosRepository
import com.cmaina.domain.models.DomainPhoto
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    private val _marsPhotos = MutableStateFlow<List<DomainPhoto>>(emptyList())
    val marsPhotos: StateFlow<List<DomainPhoto>> get() = _marsPhotos

    val photosOfMars: Flow<List<DomainPhoto>> =
        photosRepository.fetchMarsPhotosFromLocalSource().catch {
            emit(
                emptyList()
            )
        }

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            photosOfMars.collect{
                Log.d("Pichas","Mapicha ziko hapa $it")
            }
            photosRepository.fetchMarsPhotosFromLocalSource().collect {
                _marsPhotos.value = it
            }
            photosRepository.getMarsPhotosFromNetwork().collect {
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
