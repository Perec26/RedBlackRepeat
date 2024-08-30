package com.pepekprodakshn.start.ui

sealed class StartEvent {
    data object ButtonClick : StartEvent()
}
