package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import com.pepekprodakshn.frame.ui.PlayerUI

sealed class ChoosePlayerEvent {
    data class OnPlayerClick(val player: PlayerUI) : ChoosePlayerEvent()
    data object OnBackPressed : ChoosePlayerEvent()
    data object OnAddPlayerClick : ChoosePlayerEvent()
    data object OnStartMatchClick : ChoosePlayerEvent()

    // NewPlayerBottomSheet

    data class OnNameChanged(val name: String) : ChoosePlayerEvent()
    data object OnNewPlayerBottomSheetClosed : ChoosePlayerEvent()
    data object OnNewPlayerDoneClick : ChoosePlayerEvent()
}