package com.pepekprodakshn.frame.data

import com.pepekprodakshn.table.model.Ball
import com.pepekprodakshn.table.model.Foul
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FrameRepository @Inject constructor(
    private val calculator: TableStateCalculator,
) {

    private val actions = mutableListOf<FrameActionsDTO>()

    fun potBall(ball: Ball) = actions.add(FrameActionsDTO.BallPotted(ball))

    fun foul(foul: Foul) = actions.add(FrameActionsDTO.Fouled(foul))

    fun undo() = actions.removeLastOrNull()

    fun addReds(count: Int) = actions.add(FrameActionsDTO.AddReds(count))

    fun removeReds(count: Int) = actions.add(FrameActionsDTO.RemoveReds(count))

    fun endBreak() = actions.add(FrameActionsDTO.BreakEnded)

    fun getState() = calculator.calculateState(actions)

    fun restart() = actions.clear()
}