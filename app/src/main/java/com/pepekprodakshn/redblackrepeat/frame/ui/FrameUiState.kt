package com.pepekprodakshn.redblackrepeat.frame.ui

data class FrameUiState(
    val firstPlayerVO: PlayerVO = PlayerVO("Виталя"),
    val secondPlayerVO: PlayerVO = PlayerVO("Валера"),
    val firstPlayerPoints: Int = 0,
    val secondPlayerPoints: Int = 0,
    val selectedPlayer: SelectedPlayer = SelectedPlayer.FIRST,
) {

    fun addFirstPlayerPoints(points: Int) = copy(
        firstPlayerPoints = firstPlayerPoints + points
    )

    fun addSecondPlayerPoints(points: Int) = copy(
        secondPlayerPoints = secondPlayerPoints + points
    )
}

enum class SelectedPlayer { FIRST, SECOND }

data class PlayerVO(
    val name: String,
)