package com.pepekprodakshn.settings.ui

internal sealed class SettingsNavigationEvent {

    data object OnBackPress : SettingsNavigationEvent()
}