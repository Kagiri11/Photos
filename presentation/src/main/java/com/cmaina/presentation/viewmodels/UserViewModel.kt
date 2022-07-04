package com.cmaina.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.models.users.UserDomainModel
import com.cmaina.domain.usecases.FetchUserUseCase
import kotlinx.coroutines.launch

class UserViewModel(private val fetchUserUseCase: FetchUserUseCase) : ViewModel() {

    init {
        fetchUser("montylov")
    }

    private val _user = MutableLiveData<UserDomainModel>()
    val user: LiveData<UserDomainModel> get() = _user

    fun fetchUser(username: String) = viewModelScope.launch {
        fetchUserUseCase(username = username).collect {
            _user.value = it
        }
    }
}
