package com.pepekprodakshn.redblackrepeat.frame.data

data class TableStateDTO(
    val firstPlayerPoints: Int = 0,
    val secondPlayerPoints: Int = 0,
    val redsCount: Int = 15,
    val lowestPriceBall: BallDTO = BallDTO.RED,
    val nextIsColor: Boolean = false,
    val breakDTO: BreakDTO? = null,
    val isFirstPlayerSelected: Boolean = true,
) {

    val pointOnTable: Int
        get() {
            return redsCount * 8 + BallDTO.values()
                .sumOf { if (it.value > lowestPriceBall.value) it.value else 0 }
        }

    fun onBallPotted(ball: BallDTO): TableStateDTO {
        if (breakDTO?.isFreeBall == true) return onFreeBallPotted()

        val isRed = ball == BallDTO.RED

        val addFirstPlayerPoints = if (isFirstPlayerSelected) ball.value else 0
        val addSecondPlayerPoints = if (isFirstPlayerSelected) 0 else ball.value

        val newBreak = breakDTO?.add(ball) ?: BreakDTO(listOf(ball))

        val isNextInFinal = (redsCount == 0 && !isRed)

        val lowestPriceBall = if (isNextInFinal) {
            lowestPriceBall.getNextValueBall()
        } else {
            lowestPriceBall
        }

        return copy(
            redsCount = redsCount - if (isRed) 1 else 0,
            nextIsColor = isRed,
            breakDTO = newBreak,
            lowestPriceBall = lowestPriceBall,
            firstPlayerPoints = firstPlayerPoints + addFirstPlayerPoints,
            secondPlayerPoints = secondPlayerPoints + addSecondPlayerPoints
        )
    }

    private fun onFreeBallPotted(): TableStateDTO {
        val addFirstPlayerPoints = if (isFirstPlayerSelected) lowestPriceBall.value else 0
        val addSecondPlayerPoints = if (isFirstPlayerSelected) 0 else lowestPriceBall.value

        val newBreak = breakDTO?.copy(
            isFreeBall = false,
            freeBallScore = lowestPriceBall.value
        )
        return copy(
            breakDTO = newBreak,
            lowestPriceBall = lowestPriceBall,
            firstPlayerPoints = firstPlayerPoints + addFirstPlayerPoints,
            secondPlayerPoints = secondPlayerPoints + addSecondPlayerPoints
        )
    }

    fun onBreakEnded(): TableStateDTO {

        val lowestPriceBall = if (redsCount == 0 && lowestPriceBall == BallDTO.RED) {
            lowestPriceBall.getNextValueBall()
        } else {
            lowestPriceBall
        }

        return copy(
            nextIsColor = false,
            lowestPriceBall = lowestPriceBall,
            breakDTO = null,
            isFirstPlayerSelected = !isFirstPlayerSelected
        )
    }


    fun onFoul(foul: FoulDTO): TableStateDTO {

        val addFirstPlayerPoints = if (isFirstPlayerSelected) 0 else foul.points
        val addSecondPlayerPoints = if (isFirstPlayerSelected) foul.points else 0

        val newNextIsColor = if (foul.isMiss) nextIsColor else false

        val lowestPriceBall =
            if (redsCount == 0 && lowestPriceBall == BallDTO.RED && !newNextIsColor) {
                lowestPriceBall.getNextValueBall()
            } else {
                lowestPriceBall
            }

        return copy(
            breakDTO = BreakDTO(isFreeBall = true).takeIf { foul.isFreeBall },
            nextIsColor = newNextIsColor,
            lowestPriceBall = lowestPriceBall,
            isFirstPlayerSelected = foul.isMiss == isFirstPlayerSelected,
            firstPlayerPoints = firstPlayerPoints + addFirstPlayerPoints,
            secondPlayerPoints = secondPlayerPoints + addSecondPlayerPoints,
            redsCount = redsCount - foul.removeReds
        )
    }

    fun onAddReds(count: Int) = copy(redsCount = redsCount + count)

    fun onRemoveReds(count: Int) = copy(redsCount = redsCount - count)

}