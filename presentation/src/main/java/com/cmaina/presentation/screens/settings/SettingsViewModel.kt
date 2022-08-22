package com.cmaina.presentation.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmaina.presentation.activities.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(private val mainViewModel: MainViewModel) : ViewModel() {

    private val _appTheme = MutableLiveData<Boolean>()
    val appTheme: LiveData<Boolean> get() = _appTheme

    init {
        fetchAppTheme()
    }

    private fun fetchAppTheme() = viewModelScope.launch {
        mainViewModel.isAppInDarkTheme.collectLatest {
            _appTheme.value = it
        }
    }
}
