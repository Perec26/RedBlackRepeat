package com.pepekprodakshn.redblackrepeat.frame.ui.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.rounded.HighlightOff
import androidx.compose.ui.graphics.vector.ImageVector
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.frame.ui.FrameEvent

enum class FrameOptionUI(
    @StringRes val text: Int,
    val icon: ImageVector,
    val event: FrameEvent,
) {
    FOUL(
        text = R.string.frame_foul,
        icon = Icons.Rounded.HighlightOff,
        event = FrameEvent.OnFoulClick,
    ),

    UNDO(
        text = R.string.frame_undo,
        icon = Icons.AutoMirrored.Filled.Undo,
        event = FrameEvent.OnUndoClick,
    ),

    ADD_REDS(
        text = R.string.frame_add_reds,
        icon = Icons.Filled.AddCircle,
        event = FrameEvent.OnAddRedsClick,
    ),

    REMOVE_REDS(
        text = R.string.frame_remove_reds,
        icon = Icons.Filled.RemoveCircle,
        event = FrameEvent.OnRemoveRedsClick,
    ),

    RESTART(
        text = R.string.frame_restart,
        icon = Icons.Filled.RestartAlt,
        event = FrameEvent.OnRestartClick,
    ),
}