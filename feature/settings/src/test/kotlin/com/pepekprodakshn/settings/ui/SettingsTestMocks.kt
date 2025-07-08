package com.pepekprodakshn.settings.ui

import com.pepekprodakshn.settings.domain.GetUseDarkThemeUseCase
import com.pepekprodakshn.settings.domain.GetUseSystemThemeUseCase
import com.pepekprodakshn.settings.domain.UpdateUseDarkThemeUseCase
import com.pepekprodakshn.settings.domain.UpdateUseSystemThemeUseCase
import io.mockk.mockk

internal val testUpdateUseSystemThemeUseCase = mockk<UpdateUseSystemThemeUseCase>(relaxed = true)
internal val testUpdateUseDarkThemeUseCase = mockk<UpdateUseDarkThemeUseCase>(relaxed = true)
internal val testGetUseSystemThemeUseCase = mockk<GetUseSystemThemeUseCase>(relaxed = true)
internal val testGetUseDarkThemeUseCase = mockk<GetUseDarkThemeUseCase>(relaxed = true)

internal fun testViewModel(
    updateUseSystemThemeUseCase: UpdateUseSystemThemeUseCase =
        mockk<UpdateUseSystemThemeUseCase>(relaxed = true),
    updateUseDarkThemeUseCase: UpdateUseDarkThemeUseCase =
        mockk<UpdateUseDarkThemeUseCase>(relaxed = true),
    getUseSystemThemeUseCase: GetUseSystemThemeUseCase =
        mockk<GetUseSystemThemeUseCase>(relaxed = true),
    getUseDarkThemeUseCase: GetUseDarkThemeUseCase = mockk<GetUseDarkThemeUseCase>(relaxed = true),
) = SettingsViewModel(
    updateUseSystemThemeUseCase = updateUseSystemThemeUseCase,
    updateUseDarkThemeUseCase = updateUseDarkThemeUseCase,
    getUseSystemThemeUseCase = getUseSystemThemeUseCase,
    getUseDarkThemeUseCase = getUseDarkThemeUseCase,
)