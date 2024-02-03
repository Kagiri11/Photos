package com.cmaina.fotos.shared.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual open class ViewModel: ViewModel() {
    actual val viewModelScope = this.viewModelScope
}