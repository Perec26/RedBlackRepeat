package com.pepekprodakshn.redblackrepeat.frame.ui

import androidx.compose.ui.graphics.Color

data class FrameUiState(
    val firstPlayerVO: PlayerVO = PlayerVO("Виталя"),
    val secondPlayerVO: PlayerVO = PlayerVO("Валера"),
    val firstPlayerPoints: Int = 0,
    val secondPlayerPoints: Int = 0,
    val selectedPlayer: SelectedPlayer = SelectedPlayer.FIRST
) {

    fun addFirstPlayerPoints(points: Int) = copy(
        firstPlayerPoints = firstPlayerPoints + points
    )

    fun addSecondPlayerPoints(points: Int) = copy(
        secondPlayerPoints = secondPlayerPoints + points
    )

    val firstPlayerSelected = selectedPlayer == SelectedPlayer.FIRST
    val secondPlayerSelected = selectedPlayer == SelectedPlayer.SECOND

    val firstPlayerColor = if (firstPlayerSelected) Color(0xFFCCC2DC) else  Color(0x00FFFFFF)
    val secondPlayerColor = if (secondPlayerSelected) Color(0xFFCCC2DC) else  Color(0x00FFFFFF)
}

enum class SelectedPlayer { FIRST, SECOND }

data class PlayerVO(
    val name: String
)