package com.cmaina.fotos.shared.presentation.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

actual open class ViewModel actual constructor() {
    actual val viewModelScope = CoroutineScope(Dispatchers.IO)
}