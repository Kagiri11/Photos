package com.cmaina.fotos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.repository.AppRepository
import com.cmaina.domain.repository.AuthRepository
import com.cmaina.domain.repository.PhotosRepository
import com.cmaina.domain.utils.NetworkResult
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosWhite
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val appRepository: AppRepository,
    private val authRepository: AuthRepository,
    private val photosRepository: PhotosRepository
) : ViewModel() {

    private val _appTheme = MutableStateFlow(false)
    val appTheme = _appTheme.asStateFlow()

    private val _userIsAuthenticated = MutableStateFlow(false)
    val userIsAuthenticated = _userIsAuthenticated.asStateFlow()

    private val _messageToUser = MutableStateFlow(false)
    val messageToUser = _messageToUser.asStateFlow()

    init {
        fetchAppTheme()
        checkIfUserIsAuthenticated()
    }

    private fun fetchAppTheme() = viewModelScope.launch {
        appRepository.fetchAppTheme().collect {
            _appTheme.value = it
        }
    }

    fun changeAppTheme(themeToBeSet: Boolean) {
        viewModelScope.launch {
            appRepository.saveAppTheme(themeToBeSet)
        }
    }

    fun changeSystemAppBarColors(systemUiController: SystemUiController, theme: Boolean) =
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

    fun authenticateUser(authCode: String) = viewModelScope.launch {
        when (val result = authRepository.authenticateUser(authCode = authCode)) {
            is NetworkResult.Success -> {
                Log.d("OnResumeDetailer", "Calling authenticate user here on vm with success")
                // save token to persistence
//                Log.d("UserAccessToken", "Token from unsplash server: ${result.data.accessToken}")
                authRepository.saveUserAuthentication(result.data.accessToken)
            }
            is NetworkResult.Error -> {
                Log.d("OnResumeDetailer", "Calling authenticate user here on vm with failure")
            }
        }
    }
}
