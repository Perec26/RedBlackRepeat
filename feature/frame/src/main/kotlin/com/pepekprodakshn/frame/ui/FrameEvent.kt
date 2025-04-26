package com.pepekprodakshn.frame.ui

import com.pepekprodakshn.frame.ui.model.BallUI

internal sealed class FrameEvent {
    data object OnBackPressed : FrameEvent()
    data class OnBallClick(val ballUI: BallUI) : FrameEvent()
    data class OnSelectPlayer(val isFirstPlayerSelected: Boolean) : FrameEvent()

    // Foul Events

    data class OnFoulPointClick(val points: Int) : FrameEvent()
    data object OnFoulIsFreeBallClick : FrameEvent()
    data object OnFoulIsMissClick : FrameEvent()
    data object OnFoulRemoveRedsClick : FrameEvent()
    data object OnFoulConfirmClick : FrameEvent()
    data object OnFoulAddRedsClick : FrameEvent()
    data object OnFoulBottomSheetClosed : FrameEvent()

    // AddRemove Events

    data object OnAddRemoveRedsDialogClose : FrameEvent()
    data object OnAddRedsDialogClick : FrameEvent()
    data object OnRemoveRedsDialogClick : FrameEvent()
    data object OnAddRemoveRedsMinusClick : FrameEvent()
    data object OnAddRemoveRedsPlusClick : FrameEvent()

    // FrameOptions Events

    data class OnOptionsElementsCounted(val count: Int) : FrameEvent()
    data object OnFoulClick : FrameEvent()
    data object OnUndoClick : FrameEvent()
    data object OnAddRedsClick : FrameEvent()
    data object OnRemoveRedsClick : FrameEvent()
    data object OnRestartClick : FrameEvent()
    data object OnMoreClick : FrameEvent()
    data object OnFrameOptionsBottomSheetClose : FrameEvent()
    data object OnFinishClick : FrameEvent()

    // RestartFrameConfirmation Events
    data object OnRestartFrameConfirmationClosed : FrameEvent()
    data object OnRestartConfirm : FrameEvent()
}
