package com.pepekprodakshn.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferencesDataSource @Inject constructor(
    private val context: Context,
) {
    val Context.dataStore by preferencesDataStore(name = "preferences")

    fun getUseSystemThemeFlow() = context.dataStore.data.map { it[USE_SYSTEM_THEME] ?: true }
    fun getUseDarkThemeFlow() = context.dataStore.data.map { it[USE_DARK_THEME] ?: true }
    suspend fun getUseSystemTheme() = getUseSystemThemeFlow().first()
    suspend fun getUseDarkTheme() = getUseDarkThemeFlow().first()

    suspend fun updateUseSystemTheme(useSystemTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USE_SYSTEM_THEME] = useSystemTheme
        }
    }

    suspend fun updateUseDarkTheme(useDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USE_DARK_THEME] = useDarkTheme
        }
    }

    companion object {
        val USE_SYSTEM_THEME = booleanPreferencesKey("use_system_theme")
        val USE_DARK_THEME = booleanPreferencesKey("use_dark_theme")
    }
}