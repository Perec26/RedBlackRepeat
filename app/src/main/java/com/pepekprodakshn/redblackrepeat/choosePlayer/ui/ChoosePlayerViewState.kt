package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI

private const val MAX_PLAYERS = 2

data class ChoosePlayerViewState(
    val players: List<PlayerUI> = emptyList(),
    val selectedPlayers: List<PlayerUI> = emptyList(),
) {

    val isEnabled = selectedPlayers.size < MAX_PLAYERS

    fun toInitial(players: List<PlayerUI>) = copy(players = players)

    fun toSelectPlayerChange(player: PlayerUI): ChoosePlayerViewState {
        val isSelected = selectedPlayers.contains(player)
        val newList = selectedPlayers.toMutableList().apply {
            if (isSelected) remove(player) else add(player)
        }
        return copy(selectedPlayers = newList)
    }
}
