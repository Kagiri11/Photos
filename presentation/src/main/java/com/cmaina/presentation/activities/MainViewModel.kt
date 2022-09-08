package com.cmaina.presentation.activities

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.repository.AppRepository
import com.cmaina.domain.repository.AuthRepository
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosWhite
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val appRepository: AppRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _appTheme = MutableStateFlow(false)
    val appTheme = _appTheme.asStateFlow()

    private val _userIsAuthenticated = MutableStateFlow(false)
    val userIsAuthenticated = _userIsAuthenticated.asStateFlow()

    private val _messageToUser = MutableStateFlow(false)
    val messageToUser = _messageToUser.asStateFlow()

    init {
        checkIfUserIsAuthenticated()
    }

    fun fetchAppTheme(systemUiController: SystemUiController) = viewModelScope.launch {
        appRepository.fetchAppTheme().collect {
            _appTheme.value = it
            changeSystemAppBarColors(systemUiController, it)
        }
    }

    fun changeAppTheme(themeToBeSet: Boolean) {
        viewModelScope.launch {
            appRepository.saveAppTheme(themeToBeSet)
        }
    }

    private fun changeSystemAppBarColors(systemUiController: SystemUiController, theme: Boolean) =
        viewModelScope.launch {
            systemUiController.setStatusBarColor(
                when (theme) {
                    true -> FotosBlack
                    else -> FotosWhite
                }
            )
        }

    private fun checkIfUserIsAuthenticated() = viewModelScope.launch {
        authRepository.checkIfUserHasBeenAuthenticated().collect {
            _userIsAuthenticated.value = it
        }
    }

    fun changeMessageStatus() {
        _messageToUser.value = !_messageToUser.value
    }

    fun likePhoto() {
        checkIfUserIsAuthenticated()
        if (_userIsAuthenticated.value) {
            // logic to like photo
        } else {
            changeMessageStatus()
        }
    }

    fun authenticateUser(authCode: String) = viewModelScope.launch {
        when (val result = authRepository.authenticateUser(authCode = authCode)) {
            is NetworkResult.Success -> {
                // save token to persistence
                authRepository.saveUserAuthentication()
            }
            is NetworkResult.Error -> {

            }
        }
    }
}
