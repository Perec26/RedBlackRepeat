package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI

sealed class ChoosePlayerEvent {
    data class OnPlayerClick(val player: PlayerUI) : ChoosePlayerEvent()
    object OnBackPressed : ChoosePlayerEvent()
    object OnAddPlayerClick : ChoosePlayerEvent()
    object OnStartMatchClick : ChoosePlayerEvent()

    //NewPlayerBottomSheet

    data class OnNameChanged(val name: String) : ChoosePlayerEvent()
    object OnNewPlayerBottomSheetClosed : ChoosePlayerEvent()
    object OnNewPlayerDoneClick : ChoosePlayerEvent()
}