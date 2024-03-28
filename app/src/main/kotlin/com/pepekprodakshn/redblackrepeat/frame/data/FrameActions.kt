package com.pepekprodakshn.redblackrepeat.frame.data

sealed class FrameActionsDTO {
    data class BallPotted(val ball: BallDTO) : FrameActionsDTO()
    data class Foul(val foul: FoulDTO) : FrameActionsDTO()
    data class AddReds(val count: Int) : FrameActionsDTO()
    data class RemoveReds(val count: Int) : FrameActionsDTO()
    data object BreakEnded : FrameActionsDTO()
}