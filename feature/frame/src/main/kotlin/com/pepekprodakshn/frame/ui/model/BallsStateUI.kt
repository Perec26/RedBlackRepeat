package com.pepekprodakshn.frame.ui.model

internal data class BallsStateUI(
    val redsCount: Int = 15,
    val lowestPriceBall: BallUI = BallUI.RED,
    val nextIsColor: Boolean = false,
    val isFreeBall: Boolean = false,
) {
    val showRedsCount = redsCount > 1
    val redsEnabled = redsCount > 0

    val yellowEnabled = isColorBallEnabled(BallUI.YELLOW)
    val greenEnabled = isColorBallEnabled(BallUI.GREEN)
    val brownEnabled = isColorBallEnabled(BallUI.BROWN)
    val blueEnabled = isColorBallEnabled(BallUI.BLUE)
    val pinkEnabled = isColorBallEnabled(BallUI.PINK)
    val blackEnabled = isColorBallEnabled(BallUI.BLACK)

    private fun isColorBallEnabled(ballUI: BallUI) = nextIsColor ||
        lowestPriceBall == ballUI ||
        (isFreeBall && ballUI.value >= lowestPriceBall.value)
}
