package com.pepekprodakshn.frame.data

import com.pepekprodakshn.table.model.Ball
import com.pepekprodakshn.table.model.Foul
import com.pepekprodakshn.table.model.FrameBreak
import com.pepekprodakshn.table.model.TableState
import com.pepekprodakshn.table.model.getNextValueBall
import javax.inject.Inject

internal class TableStateCalculator @Inject constructor() {

    fun calculateState(actions: List<FrameActionsDTO>): TableState {
        var resultState = TableState()
        actions.forEach { resultState = updateFrameState(it, resultState) }
        return resultState
    }

    private fun updateFrameState(
        action: FrameActionsDTO,
        resultState: TableState,
    ) = when (action) {
        is FrameActionsDTO.BallPotted -> resultState.onBallPotted(action.ball)
        is FrameActionsDTO.Fouled -> resultState.onFoul(action.foul)
        is FrameActionsDTO.AddReds -> resultState.onAddReds(action.count)
        is FrameActionsDTO.RemoveReds -> resultState.onRemoveReds(action.count)
        FrameActionsDTO.BreakEnded -> resultState.onBreakEnded()
    }

    private fun TableState.onBallPotted(ball: Ball): TableState {
        if (frameBreak?.isFreeBall == true) return onFreeBallPotted()

        val isRed = ball == Ball.RED

        val addFirstPlayerPoints = if (isFirstPlayerSelected) ball.points else 0
        val addSecondPlayerPoints = if (isFirstPlayerSelected) 0 else ball.points

        val newFirstPlayerPoints = firstPlayerPoints + addFirstPlayerPoints
        val newSecondPlayerPoints = secondPlayerPoints + addSecondPlayerPoints
        val isDraw = newFirstPlayerPoints == newSecondPlayerPoints

        val newBreak = frameBreak?.add(ball) ?: FrameBreak(listOf(ball))

        val isNextInFinal = (redsCount == 0 && !isRed)

        val lowestPriceBall = if (isNextInFinal) {
            lowestValueBall.getNextValueBall() ?: if (isDraw) Ball.BLACK else Ball.RED
        } else {
            lowestValueBall
        }

        return copy(
            redsCount = redsCount - if (isRed) 1 else 0,
            nextIsColor = isRed,
            frameBreak = newBreak,
            lowestValueBall = lowestPriceBall,
            firstPlayerPoints = firstPlayerPoints + addFirstPlayerPoints,
            secondPlayerPoints = secondPlayerPoints + addSecondPlayerPoints,
        )
    }

    private fun TableState.onFreeBallPotted(): TableState {
        val addFirstPlayerPoints = if (isFirstPlayerSelected) lowestValueBall.points else 0
        val addSecondPlayerPoints = if (isFirstPlayerSelected) 0 else lowestValueBall.points

        val newBreak = frameBreak?.copy(
            isFreeBall = false,
            freeBallScore = lowestValueBall.points,
        )
        return copy(
            frameBreak = newBreak,
            nextIsColor = redsCount > 0,
            firstPlayerPoints = firstPlayerPoints + addFirstPlayerPoints,
            secondPlayerPoints = secondPlayerPoints + addSecondPlayerPoints,
        )
    }

    private fun TableState.onBreakEnded(): TableState {

        val lowestPriceBall = if (redsCount == 0 && lowestValueBall == Ball.RED) {
            lowestValueBall.getNextValueBall() ?: Ball.RED
        } else {
            lowestValueBall
        }

        return copy(
            nextIsColor = false,
            lowestValueBall = lowestPriceBall,
            frameBreak = null,
            isFirstPlayerSelected = !isFirstPlayerSelected,
        )
    }

    private fun TableState.onFoul(foul: Foul): TableState {

        val addFirstPlayerPoints = if (isFirstPlayerSelected) 0 else foul.points
        val addSecondPlayerPoints = if (isFirstPlayerSelected) foul.points else 0

        val newNextIsColor = if (foul.isMiss) nextIsColor else false

        val lowestPriceBall =
            if (redsCount == 0 && lowestValueBall == Ball.RED && !newNextIsColor) {
                lowestValueBall.getNextValueBall() ?: Ball.RED
            } else {
                lowestValueBall
            }

        return copy(
            frameBreak = FrameBreak(isFreeBall = true).takeIf { foul.isFreeBall },
            nextIsColor = newNextIsColor,
            lowestValueBall = lowestPriceBall,
            isFirstPlayerSelected = foul.isMiss == isFirstPlayerSelected,
            firstPlayerPoints = firstPlayerPoints + addFirstPlayerPoints,
            secondPlayerPoints = secondPlayerPoints + addSecondPlayerPoints,
            redsCount = redsCount - foul.removeReds,
        )
    }

    private fun TableState.onAddReds(count: Int) = copy(redsCount = redsCount + count)

    private fun TableState.onRemoveReds(count: Int): TableState {
        val newRedsCount = redsCount - count
        val lowestPriceBall = if (newRedsCount > 0) lowestValueBall else Ball.YELLOW
        return copy(
            redsCount = newRedsCount,
            lowestValueBall = lowestPriceBall,
        )
    }
}