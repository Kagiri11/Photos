package com.cmaina.fotos.shared.utils

import com.russhwolf.settings.PropertiesSettings
import com.russhwolf.settings.Settings
import java.util.Properties

val delegate: Properties // ...
    get() {
        TODO()
    }
actual val PhotosSettings: Settings = PropertiesSettings(delegate)