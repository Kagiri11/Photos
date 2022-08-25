package com.cmaina.presentation.activities

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosWhite
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _isAppInDarkTheme = MutableStateFlow(false)
    val isAppInDarkTheme = _isAppInDarkTheme.asStateFlow()

    val appTheme = booleanPreferencesKey("appTheme")

    fun fetchAppTheme(theme: Flow<Preferences>) = viewModelScope.launch {
        theme.map { it[appTheme] }.collect {
            Log.d("MyTheme", "Is app in dark theme: $it")
            it?.let {
                _isAppInDarkTheme.value = it
            }
        }
    }

    fun changeAppTheme(dataStore: DataStore<Preferences>, theme: Boolean) {
        viewModelScope.launch {
            _isAppInDarkTheme.value = theme
            dataStore.edit { settings ->
                settings[appTheme] = _isAppInDarkTheme.value
            }
        }
    }

    fun changeSystemAppBarColors(systemUiController: SystemUiController) = viewModelScope.launch {
        _isAppInDarkTheme.collect {
            systemUiController.setSystemBarsColor(
                if (it) {
                    FotosBlack
                } else {
                    FotosWhite
                }
            )
        }
    }
}
