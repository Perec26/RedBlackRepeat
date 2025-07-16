package com.pepekprodakshn.frame.domain.mapper

import com.pepekprodakshn.frame.ui.model.BallsStateUI
import com.pepekprodakshn.frame.ui.model.FrameInfoUI
import com.pepekprodakshn.frame.ui.model.TableStateUI
import com.pepekprodakshn.table.model.TableState
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max

internal fun TableState.toUI() = TableStateUI(
    firstPlayerPoints = firstPlayerPoints,
    secondPlayerPoints = secondPlayerPoints,
    ballState = BallsStateUI(
        redsCount = redsCount,
        lowestPriceBall = lowestPriceBall.toUI(),
        nextIsColor = nextIsColor,
        isFreeBall = frameBreak?.isFreeBall ?: false,
    ),
    breakUI = frameBreak?.toUI(),
    isFirstPlayerSelected = isFirstPlayerSelected,
    frameInfoUI = getFrameInfo(this),
)

private fun getFrameInfo(state: TableState): FrameInfoUI {

    val pointsOnTable = if (state.redsCount > 0) {
        state.redsCount * 8 + 27
    } else {
        (max(state.lowestPriceBall.value, 2)..7).sum()
    }
        .let { if (state.nextIsColor) it + 7 else it }

    val difference = abs(state.firstPlayerPoints - state.secondPlayerPoints)

    val snookersRequired = if (difference < pointsOnTable) {
        0
    } else {
        val diff = difference - pointsOnTable
        val lowestFoul =
            if (state.lowestPriceBall.value <= 4) 4 else state.lowestPriceBall.value
        ceil(diff.toFloat() / lowestFoul).toInt()
    }
    return FrameInfoUI(
        pointsOnTable = pointsOnTable,
        snookersRequired = snookersRequired,
    )
}
