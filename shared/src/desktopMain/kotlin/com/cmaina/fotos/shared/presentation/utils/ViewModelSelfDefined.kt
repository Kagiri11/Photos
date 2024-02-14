package com.cmaina.fotos.shared.presentation.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

actual open class ViewModelSelfDefined actual constructor() {
    actual val viewModelScope = CoroutineScope(Dispatchers.IO)
}