package com.cmaina.fotos.shared.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.cmaina.fotos.shared.domain.repositories.AppRepository
import com.cmaina.fotos.shared.utils.AppSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class AppRepositoryImpl(
    private val settings: AppSettings
) : AppRepository {

    override suspend fun fetchAppTheme(): Flow<Boolean> {
        return flowOf(settings.getBoolean("appTheme"))
    }

    override suspend fun saveAppTheme(appTheme: Boolean) =
        settings.putBoolean(key = "appTheme", value = appTheme)
}
