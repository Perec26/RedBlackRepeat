package com.pepekprodakshn.redblackrepeat.frame.data

data class BreakDTO(
    val balls: List<BallDTO> = emptyList(),
    val isFreeBall: Boolean = false,
    val freeBallScore: Int = 0,
) {

    fun add(ballDTO: BallDTO): BreakDTO {
        val newBalls = balls.toMutableList()
        newBalls.add(ballDTO)

        return copy(balls = newBalls)
    }
}