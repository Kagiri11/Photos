package com.cmaina.fotos.shared.presentation.utils

import kotlinx.coroutines.CoroutineScope

expect open class ViewModelSelfDefined() {
    val viewModelScope: CoroutineScope
}