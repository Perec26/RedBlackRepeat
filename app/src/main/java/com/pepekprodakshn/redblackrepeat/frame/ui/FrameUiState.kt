package com.pepekprodakshn.redblackrepeat.frame.ui

import androidx.compose.runtime.Stable

@Stable
data class FrameUiState(
    val firstPlayerUI: PlayerUI = PlayerUI(1, "Виталя"),
    val secondPlayerUI: PlayerUI = PlayerUI(2,
        "Валера"),
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

    fun selectPlayer(selectedPlayer: SelectedPlayer) = copy(
        selectedPlayer = selectedPlayer
    )
}

enum class SelectedPlayer { FIRST, SECOND }

data class PlayerUI(
    val id: Int,
    val name: String,
)