package com.pepekprodakshn.frame.ui.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class BreakUI(
    val balls: List<BallUI> = emptyList(),
    val isFreeBall: Boolean = false,
    val freeBallScore: Int = 0,
) {

    val sum = balls.sumOf(BallUI::value) + freeBallScore

    val reds = balls.count { it == BallUI.RED }
    val yellows = balls.count { it == BallUI.YELLOW }
    val greens = balls.count { it == BallUI.GREEN }
    val browns = balls.count { it == BallUI.BROWN }
    val blues = balls.count { it == BallUI.BLUE }
    val pinks = balls.count { it == BallUI.PINK }
    val blacks = balls.count { it == BallUI.BLACK }

    fun add(ball: BallUI): BreakUI {
        val newBalls = balls.toMutableList()
        newBalls.add(ball)
        return copy(balls = newBalls)
    }
}
