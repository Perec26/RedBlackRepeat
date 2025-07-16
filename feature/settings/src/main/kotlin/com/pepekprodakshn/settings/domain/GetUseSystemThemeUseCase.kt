package com.pepekprodakshn.settings.domain

import com.pepekprodakshn.settings.data.repository.SettingsRepository
import javax.inject.Inject

internal class GetUseSystemThemeUseCase @Inject constructor(
    private val repository: SettingsRepository,
) {

    suspend fun execute() = repository.getUseSystemTheme()
}