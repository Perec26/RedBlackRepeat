package com.pepekprodakshn.settings.ui

internal sealed class SettingsEvent {

    data object OnBackPressed : SettingsEvent()
    data object OnUseSystemThemeClick : SettingsEvent()
    data object OnUseDarkThemeClick : SettingsEvent()
}
