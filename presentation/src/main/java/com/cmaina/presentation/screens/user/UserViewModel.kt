package com.cmaina.presentation.screens.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.cmaina.domain.models.photos.DomainPhotoListItem
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.repository.UsersRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _user = MutableLiveData<UserDomainModel>()
    val user: LiveData<UserDomainModel> get() = _user

    private val _userPhotos = MutableLiveData<Flow<PagingData<DomainPhotoListItem>>>()
    val userPhotos: LiveData<Flow<PagingData<DomainPhotoListItem>>> get() = _userPhotos

    fun fetchUser(username: String) = viewModelScope.launch {
        usersRepository.fetchUser(username = username).collect {
            _user.value = it
        }
    }

    fun fetchUserPhotos(username: String) = viewModelScope.launch {
        Log.d("UserDomainPhotos", "This has been called")
        usersRepository.fetchUserPhotos(username = username).let {
            delay(200)
            _userPhotos.value = it
        }
    }
}
