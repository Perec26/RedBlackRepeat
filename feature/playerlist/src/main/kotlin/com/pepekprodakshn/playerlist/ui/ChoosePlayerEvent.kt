package com.pepekprodakshn.playerlist.ui

import com.pepekprodakshn.playerlist.ui.model.PlayerUI

internal sealed class ChoosePlayerEvent {
    data class OnPlayerClick(val player: PlayerUI) : ChoosePlayerEvent()
    data object OnBackPressed : ChoosePlayerEvent()
    data object OnAddPlayerClick : ChoosePlayerEvent()
    data object OnStartMatchClick : ChoosePlayerEvent()

    // NewPlayerBottomSheet

    data class OnNameChanged(val name: String) : ChoosePlayerEvent()
    data object OnNewPlayerBottomSheetClosed : ChoosePlayerEvent()
    data object OnNewPlayerDoneClick : ChoosePlayerEvent()
}