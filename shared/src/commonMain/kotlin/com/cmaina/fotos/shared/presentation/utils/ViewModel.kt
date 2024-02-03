package com.cmaina.fotos.shared.presentation.utils

import kotlinx.coroutines.CoroutineScope

expect open class ViewModel() {
    val coroutineScope: CoroutineScope
}