package com.pepekprodakshn.start.ui

internal sealed class StartNavigationEvent {
    data object OnStartClick : StartNavigationEvent()
}