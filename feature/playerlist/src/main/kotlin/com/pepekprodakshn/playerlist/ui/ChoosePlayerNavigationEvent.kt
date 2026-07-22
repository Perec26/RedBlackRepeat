package com.pepekprodakshn.playerlist.ui

internal sealed class ChoosePlayerNavigationEvent {
    data object OnBackPress : ChoosePlayerNavigationEvent()
    data class OnStartFrameClick(val firstPlayerId: Int, val secondPlayerId: Int) :
        ChoosePlayerNavigationEvent()
}
