package com.cmaina.presentation.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.domain.repository.AppRepository
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosWhite
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val appRepository: AppRepository) : ViewModel() {

    private val _appTheme = MutableStateFlow(false)
    val appTheme = _appTheme.asStateFlow()

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

    fun changeSystemAppBarColors(systemUiController: SystemUiController, theme: Boolean) =
        viewModelScope.launch {
            systemUiController.setStatusBarColor(
                when (theme) {
                    true -> FotosBlack
                    else -> FotosWhite
                }
            )
        }
}

private fun String?.isFotosInDarkTheme(): Boolean {
    return !(this.isNullOrEmpty() || this.equals("light", true))
}
