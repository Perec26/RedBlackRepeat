package com.pepekprodakshn.frame.domain.mapper

import com.pepekprodakshn.frame.ui.model.BallsStateUI
import com.pepekprodakshn.frame.ui.model.FrameInfoUI
import com.pepekprodakshn.frame.ui.model.TableStateUI
import com.pepekprodakshn.table.model.TableState
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max

private const val POINTS_FOR_RED_PLUS_COLOR = 8
private const val POINTS_FOR_ALL_COLORS = 27 // 2+3+4+5+6+7
private const val MIN_COLOR_BALL_VALUE = 2 // Yellow
private const val MAX_COLOR_BALL_VALUE = 7 // Black
private const val MIN_FOUL_POINTS_WHEN_COLORS_ON = 4

internal fun TableState.toUI() = TableStateUI(
    firstPlayerPoints = firstPlayerPoints,
    secondPlayerPoints = secondPlayerPoints,
    ballState = BallsStateUI(
        redsCount = redsCount,
        lowestPriceBall = lowestValueBall.toUI(),
        nextIsColor = nextIsColor,
        isFreeBall = frameBreak?.isFreeBall ?: false,
    ),
    breakUI = frameBreak?.toUI(),
    isFirstPlayerSelected = isFirstPlayerSelected,
    frameInfoUI = getFrameInfo(this),
)

private fun getFrameInfo(state: TableState): FrameInfoUI {

    val pointsOnTable = calculatePointsOnTable(state)
    val difference = abs(state.firstPlayerPoints - state.secondPlayerPoints)
    val snookersRequired = calculateSnookersRequired(
        pointsNeededFromFouls = difference - pointsOnTable,
        state = state,
    )

    return FrameInfoUI(
        pointsOnTable = pointsOnTable,
        snookersRequired = snookersRequired,
    )
}

private fun calculatePointsOnTable(state: TableState) = if (state.redsCount > 0) {
    state.redsCount * POINTS_FOR_RED_PLUS_COLOR + POINTS_FOR_ALL_COLORS
} else {
    (max(state.lowestValueBall.points, MIN_COLOR_BALL_VALUE)..MAX_COLOR_BALL_VALUE).sum()
}
    .let { if (state.nextIsColor) it + MAX_COLOR_BALL_VALUE else it }

private fun calculateSnookersRequired(pointsNeededFromFouls: Int, state: TableState): Int {
    if (pointsNeededFromFouls < 1) return 0
    val minimumPointsFromFoul = max(state.lowestValueBall.points, MIN_FOUL_POINTS_WHEN_COLORS_ON)
    return ceil(pointsNeededFromFouls.toFloat() / minimumPointsFromFoul).toInt()
}
