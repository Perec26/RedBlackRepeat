package com.pepekprodakshn.redblackrepeat.frame.ui

import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallUI

sealed class FrameEvent {
    data class OnBallClick(val ballUI: BallUI) : FrameEvent()
    data class OnSelectPlayer(val isFirstPlayerSelected: Boolean) : FrameEvent()


    //Foul Events

    data class OnFoulPointClick(val points: Int) : FrameEvent()
    object OnFoulIsFreeBallClick : FrameEvent()
    object OnFoulIsMissClick : FrameEvent()
    object OnFoulRemoveRedsClick : FrameEvent()
    object OnFoulConfirmClick : FrameEvent()
    object OnFoulAddRedsClick : FrameEvent()
    object OnFoulBottomSheetClosed : FrameEvent()

    //AddRemove Events

    object OnAddRemoveRedsDialogClose : FrameEvent()
    object OnAddRedsDialogClick : FrameEvent()
    object OnRemoveRedsDialogClick : FrameEvent()
    object OnAddRemoveRedsMinusClick : FrameEvent()
    object OnAddRemoveRedsPlusClick : FrameEvent()

    //FrameOptions Events

    data class OnOptionsElementsCounted(val count: Int) : FrameEvent()
    object OnFoulClick : FrameEvent()
    object OnUndoClick : FrameEvent()
    object OnAddRedsClick : FrameEvent()
    object OnRemoveRedsClick : FrameEvent()
    object OnRestartClick : FrameEvent()
    object OnMoreClick : FrameEvent()
    object OnFrameOptionsBottomSheetClose : FrameEvent()

}
