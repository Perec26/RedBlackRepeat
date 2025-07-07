package com.pepekprodakshn.settings.ui

internal data class SettingsUiState(
    val useSystemTheme: Boolean = true,
    val useDarkTheme: Boolean = true,
) {

    fun updateUseSystemTheme(useSystemTheme: Boolean) = copy(useSystemTheme = useSystemTheme)

    fun updateUseDarkTheme(useDarkTheme: Boolean) = copy(useDarkTheme = useDarkTheme)
}
