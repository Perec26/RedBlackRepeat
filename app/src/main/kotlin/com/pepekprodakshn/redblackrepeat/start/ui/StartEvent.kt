package com.pepekprodakshn.redblackrepeat.start.ui

sealed class StartEvent {
    data object ButtonClick : StartEvent()
}
