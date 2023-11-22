package com.pepekprodakshn.redblackrepeat.frame.ui

import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallUI

sealed class FrameEvent {
    data class OnBallClick(val ballUI: BallUI) : FrameEvent()
    data class OnFoulClick(val foul: Int) : FrameEvent()
    data class OnRemoveClick(val remove: Int) : FrameEvent()
    data class OnSelectPlayer(val selectedPlayer: SelectedPlayer) : FrameEvent()
}
