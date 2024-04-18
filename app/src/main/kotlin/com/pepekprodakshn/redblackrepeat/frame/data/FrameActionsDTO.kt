package com.pepekprodakshn.redblackrepeat.frame.data

import com.pepekprodakshn.table.model.Ball
import com.pepekprodakshn.table.model.Foul

sealed class FrameActionsDTO {
    data class BallPotted(val ball: Ball) : FrameActionsDTO()
    data class Fouled(val foul: Foul) : FrameActionsDTO()
    data class AddReds(val count: Int) : FrameActionsDTO()
    data class RemoveReds(val count: Int) : FrameActionsDTO()
    data object BreakEnded : FrameActionsDTO()
}