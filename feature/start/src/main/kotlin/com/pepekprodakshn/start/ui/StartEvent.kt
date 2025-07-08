package com.pepekprodakshn.start.ui

internal sealed class StartEvent {
    data object OnStartClick : StartEvent()
    data object OnSettingsClick : StartEvent()
}
