package com.pepekprodakshn.settings.data.repository

import com.pepekprodakshn.preferences.PreferencesDataSource
import javax.inject.Inject

internal class SettingsRepository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) {
    suspend fun updateUseSystemTheme(useSystemTheme: Boolean) =
        preferencesDataSource.updateUseSystemTheme(useSystemTheme)

    suspend fun updateUseDarkTheme(useDarkTheme: Boolean) =
        preferencesDataSource.updateUseDarkTheme(useDarkTheme)

    suspend fun getUseSystemTheme() = preferencesDataSource.getUseSystemTheme()

    suspend fun getUseDarkTheme() = preferencesDataSource.getUseDarkTheme()
}