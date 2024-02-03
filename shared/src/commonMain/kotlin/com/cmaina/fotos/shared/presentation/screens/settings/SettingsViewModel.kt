package com.cmaina.fotos.shared.presentation.screens.settings

import com.cmaina.fotos.shared.domain.repositories.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.cmaina.fotos.shared.presentation.utils.ViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val appRepository: AppRepository) : ViewModel() {

    private val _appTheme = MutableStateFlow(false)
    val appTheme = _appTheme.asStateFlow()

    private val _isThemeDialogOpen = MutableStateFlow(false)
    val isThemeDialogOpen = _isThemeDialogOpen.asStateFlow()

    fun changeDialogOpenState() {
        _isThemeDialogOpen.value = !_isThemeDialogOpen.value
    }

    init {
        fetchAppTheme()
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
}
