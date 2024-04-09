package com.pepekprodakshn.redblackrepeat.frame.data

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FrameDataSource @Inject constructor() {

    private val actions = mutableListOf<FrameActionsDTO>()

    fun potBall(ballDTO: BallDTO) = actions.add(FrameActionsDTO.BallPotted(ballDTO))

    fun foul(foulDTO: FoulDTO) = actions.add(FrameActionsDTO.Foul(foulDTO))

    fun endBreak() = actions.add(FrameActionsDTO.BreakEnded)

    fun undoAction() = actions.removeLast()

    fun addReds(count: Int) = actions.add(FrameActionsDTO.AddReds(count))

    fun removeReds(count: Int) = actions.add(FrameActionsDTO.RemoveReds(count))

    fun restart() = actions.clear()

    fun calculateState(): TableStateDTO {
        var resultState = TableStateDTO()
        actions.forEach { resultState = updateFrameState(it, resultState) }
        return resultState
    }

    private fun updateFrameState(
        action: FrameActionsDTO,
        resultState: TableStateDTO,
    ) = when (action) {
        is FrameActionsDTO.BallPotted -> resultState.onBallPotted(action.ball)
        is FrameActionsDTO.Foul -> resultState.onFoul(action.foul)
        is FrameActionsDTO.AddReds -> resultState.onAddReds(action.count)
        is FrameActionsDTO.RemoveReds -> resultState.onRemoveReds(action.count)
        FrameActionsDTO.BreakEnded -> resultState.onBreakEnded()
    }
}