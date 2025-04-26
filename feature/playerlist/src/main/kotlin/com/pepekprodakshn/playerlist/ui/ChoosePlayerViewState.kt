package com.pepekprodakshn.playerlist.ui

import com.pepekprodakshn.playerlist.ui.model.PlayerUI
import com.pepekprodakshn.ui.ListState

private const val MAX_PLAYERS = 2

internal data class ChoosePlayerViewState(
    val players: List<PlayerUI> = emptyList(),
    val showNewPlayerBottomSheet: Boolean = false,
    val newPlayerName: String = "",
    val isNewPlayerError: Boolean = false,
    val selectedPlayers: List<PlayerUI> = emptyList(),
    val listState: ListState = ListState.LOADING,
) {

    val isEnabled = selectedPlayers.size < MAX_PLAYERS

    fun updatePlayers(players: List<PlayerUI>) = copy(
        players = players,
        listState = if (players.isEmpty()) ListState.EMPTY else ListState.READY,
    )

    fun changePlayerSelection(player: PlayerUI): ChoosePlayerViewState {
        val isSelected = selectedPlayers.contains(player)
        val newList = selectedPlayers.toMutableList().apply {
            if (isSelected) remove(player) else add(player)
        }
        return copy(selectedPlayers = newList)
    }

    fun openNewPlayerBottomSheet() = copy(showNewPlayerBottomSheet = true)

    fun closeNewPlayerBottomSheet() = copy(showNewPlayerBottomSheet = false)

    fun updateNewPlayerName(newName: String) = copy(
        newPlayerName = newName,
        isNewPlayerError = false,
    )

    fun showNewNameValidationError() = copy(isNewPlayerError = true)

    fun setEmptyNewPlayerState() = copy(
        newPlayerName = "",
        showNewPlayerBottomSheet = false,
    )
}
