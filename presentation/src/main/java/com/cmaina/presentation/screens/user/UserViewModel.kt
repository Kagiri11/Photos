package com.cmaina.presentation.screens.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.usecases.FetchUserPhotosUseCase
import com.cmaina.domain.usecases.FetchUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(
    private val fetchUserUseCase: FetchUserUseCase,
    private val fetchUserPhotosUseCase: FetchUserPhotosUseCase
) : ViewModel() {

    private val _user = MutableLiveData<UserDomainModel>()
    val user: LiveData<UserDomainModel> get() = _user

    private val _userPhotos = MutableLiveData<Flow<PagingData<DomainPhotoListItem>>>()
    val userPhotos: LiveData<Flow<PagingData<DomainPhotoListItem>>> get() = _userPhotos

    fun fetchUser(username: String) = viewModelScope.launch {
        fetchUserUseCase(username = username).collect {
            _user.value = it
        }
    }

    fun fetchUserPhotos(username: String) = viewModelScope.launch {
        Log.d("UserDomainPhotos", "This has been called")
        fetchUserPhotosUseCase(username = username).let {
            delay(200)
            Log.d("UserDomainPhotos", "User photos in vm: $it")
            _userPhotos.value = it
        }
    }
}
