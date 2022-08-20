package com.cmaina.presentation.screens.settings

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    private val _appTheme = MutableStateFlow(0)
    val appTheme = _appTheme.asStateFlow()

    fun fetchAppTheme(theme: Flow<Preferences>) = viewModelScope.launch {
        val appTheme = intPreferencesKey("appTheme")
        theme.map { it[appTheme] ?: 0 }.collect{
            _appTheme.value = it
        }
    }
}
