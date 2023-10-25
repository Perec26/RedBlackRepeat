package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI

sealed class ChoosePlayerEvent {
    object OnBackPressed : ChoosePlayerEvent()
    object OnAddPlayerClick : ChoosePlayerEvent()
    data class OnPlayerClick(val player: PlayerUI) : ChoosePlayerEvent()
}
