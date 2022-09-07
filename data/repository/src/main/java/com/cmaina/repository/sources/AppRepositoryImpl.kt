package com.cmaina.repository.sources

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.cmaina.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepositoryImpl(
    private val preferences: DataStore<Preferences>,
) : AppRepository {
    private val fotosAppTheme = booleanPreferencesKey("appTheme")

    override suspend fun fetchAppTheme(): Flow<Boolean> {
        val themePreference: Flow<Boolean> = preferences.data.map {
            it[fotosAppTheme] ?: false
        }
        return themePreference
    }

    override suspend fun saveAppTheme(appTheme: Boolean) {
        preferences.edit { settings ->
            settings[fotosAppTheme] = appTheme
        }
    }
}
