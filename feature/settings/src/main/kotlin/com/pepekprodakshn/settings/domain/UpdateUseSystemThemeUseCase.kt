package com.pepekprodakshn.settings.domain

import com.pepekprodakshn.settings.data.repository.SettingsRepository
import javax.inject.Inject

internal class UpdateUseSystemThemeUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {

    suspend fun execute(useSystemTheme: Boolean) = repository.updateUseSystemTheme(useSystemTheme)
}