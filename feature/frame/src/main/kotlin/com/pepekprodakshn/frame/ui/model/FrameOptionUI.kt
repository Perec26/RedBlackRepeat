package com.pepekprodakshn.frame.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.pepekprodakshn.designsystem.R.drawable
import com.pepekprodakshn.frame.R
import com.pepekprodakshn.frame.ui.FrameEvent

internal enum class FrameOptionUI(
    @StringRes val text: Int,
    @DrawableRes val icon: Int,
    val event: FrameEvent,
) {
    FOUL(
        text = R.string.frame_foul,
        icon = drawable.highlight_off_24,
        event = FrameEvent.OnFoulClick,
    ),

    UNDO(
        text = R.string.frame_undo,
        icon = drawable.undo_24,
        event = FrameEvent.OnUndoClick,
    ),

    ADD_REDS(
        text = R.string.frame_add_reds,
        icon = drawable.add_circle_24,
        event = FrameEvent.OnAddRedsClick,
    ),

    REMOVE_REDS(
        text = R.string.frame_remove_reds,
        icon = drawable.remove_circle_24,
        event = FrameEvent.OnRemoveRedsClick,
    ),

    RESTART(
        text = R.string.frame_restart,
        icon = drawable.restart_alt_24,
        event = FrameEvent.OnRestartClick,
    ),

    FINISH(
        text = R.string.frame_finish,
        icon = drawable.close_24,
        event = FrameEvent.OnFinishClick,
    ),
}
