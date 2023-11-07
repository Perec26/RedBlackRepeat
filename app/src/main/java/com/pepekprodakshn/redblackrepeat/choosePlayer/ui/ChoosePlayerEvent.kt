package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI

sealed class ChoosePlayerEvent {
    object OnBackPressed : ChoosePlayerEvent()
    object OnAddPlayerClick : ChoosePlayerEvent()
    data class OnPlayerClick(val player: PlayerUI) : ChoosePlayerEvent()
    data class OnNameChanged(val name: String) : ChoosePlayerEvent()
    object OnNewPlayerBottomSheetClosed : ChoosePlayerEvent()
    object OnNewPlayerDoneClick : ChoosePlayerEvent()
    object OnStartMatchClick : ChoosePlayerEvent()
}
