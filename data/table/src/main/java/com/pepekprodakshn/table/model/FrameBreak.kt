package com.pepekprodakshn.table.model

data class FrameBreak(
    val balls: List<Ball> = emptyList(),
    val isFreeBall: Boolean = false,
    val freeBallScore: Int = 0,
) {

    fun add(ball: Ball): FrameBreak {
        val newBalls = balls.toMutableList()
        newBalls.add(ball)

        return copy(balls = newBalls)
    }
}