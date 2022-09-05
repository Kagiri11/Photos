package com.cmaina.presentation.screens.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.repository.AuthRepository
import com.cmaina.domain.utils.NetworkResult
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> get() = _isUserAuthenticated

    fun authenticateUser(authCode: String) = viewModelScope.launch {
        when (val result = authRepository.authenticateUser(authCode = authCode)) {
            is NetworkResult.Success -> {
                _isUserAuthenticated.value = true
                // save token to persistence
            }
            is NetworkResult.Error -> {
                _isUserAuthenticated.value = false
            }
        }
    }
}
