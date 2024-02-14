package com.cmaina.fotos.shared.data.cache.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

//val Context.fotosDataStorePreferences: DataStore<Preferences> by preferencesDataStore(name = "settings")
fun provideDataStore(context: Context): DataStore<Preferences> = context.fotosDataStorePreferences
