package com.pepekprodakshn.redblackrepeat.frame.ui

import androidx.compose.runtime.Stable
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallUI
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BreakUI

@Stable
data class FrameUiState(
    val firstPlayerUI: PlayerUI = PlayerUI(0, ""),
    val secondPlayerUI: PlayerUI = PlayerUI(0, ""),
    val firstPlayerPoints: Int = 0,
    val secondPlayerPoints: Int = 0,
    val breakUI: BreakUI? = null,
    val selectedPlayer: SelectedPlayer = SelectedPlayer.FIRST,
) {

    fun addFirstPlayerPoints(points: Int) = copy(
        firstPlayerPoints = firstPlayerPoints + points
    )

    fun onBallPotted(ballUI: BallUI): FrameUiState {
        val (firstPlayerAddPoints, secondPlayerAddPoints) = when (selectedPlayer) {
            SelectedPlayer.FIRST -> ballUI.value to 0
            SelectedPlayer.SECOND -> 0 to ballUI.value
        }
        val newBreak = breakUI?.add(ballUI) ?: BreakUI(listOf(ballUI))

        return copy(
            firstPlayerPoints = firstPlayerPoints + firstPlayerAddPoints,
            secondPlayerPoints = secondPlayerPoints + secondPlayerAddPoints,
            breakUI = newBreak
        )
    }

    fun addSecondPlayerPoints(points: Int) = copy(
        secondPlayerPoints = secondPlayerPoints + points
    )

    fun selectPlayer(selectedPlayer: SelectedPlayer) = copy(
        selectedPlayer = selectedPlayer,
        breakUI = null
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