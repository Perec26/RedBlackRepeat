package com.pepekprodakshn.frame.ui

sealed class FrameNavigationEvent {
    data object OnBackPressed : FrameNavigationEvent()
}