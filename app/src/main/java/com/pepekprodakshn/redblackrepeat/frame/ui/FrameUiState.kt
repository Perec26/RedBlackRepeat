package com.pepekprodakshn.redblackrepeat.frame.ui

import androidx.compose.runtime.Stable

@Stable
data class FrameUiState(
    val firstPlayerUI: PlayerUI = PlayerUI(0, ""),
    val secondPlayerUI: PlayerUI = PlayerUI(0, ""),
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

    fun initPlayers(
        firstPlayerUI: PlayerUI,
        secondPlayerUI: PlayerUI,
    ) = copy(
        firstPlayerUI = firstPlayerUI,
        secondPlayerUI = secondPlayerUI
    )
}

enum class SelectedPlayer { FIRST, SECOND }

data class PlayerUI(
    val id: Int,
    val name: String,
)