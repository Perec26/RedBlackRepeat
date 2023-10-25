package com.pepekprodakshn.redblackrepeat.frame.ui

import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallVO

sealed class FrameEvent {
    data class OnBallClick(val ballVO: BallVO) : FrameEvent()
    data class OnFoulClick(val foul: Int) : FrameEvent()
    data class OnRemoveClick(val remove: Int) : FrameEvent()
    data class OnSelectPlayer(val selectedPlayer: SelectedPlayer) : FrameEvent()
}
