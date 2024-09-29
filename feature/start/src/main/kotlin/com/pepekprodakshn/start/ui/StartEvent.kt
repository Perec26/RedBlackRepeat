package com.pepekprodakshn.start.ui

internal sealed class StartEvent {
    data object ButtonClick : StartEvent()
}
