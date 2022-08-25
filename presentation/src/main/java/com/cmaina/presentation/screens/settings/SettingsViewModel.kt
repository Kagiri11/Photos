package com.cmaina.presentation.screens.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel : ViewModel() {

    private val _isThemeDialogOpen = MutableStateFlow(false)
    val isThemeDialogOpen = _isThemeDialogOpen.asStateFlow()

    fun changeDialogOpenState() {
        _isThemeDialogOpen.value = !_isThemeDialogOpen.value
    }
}
