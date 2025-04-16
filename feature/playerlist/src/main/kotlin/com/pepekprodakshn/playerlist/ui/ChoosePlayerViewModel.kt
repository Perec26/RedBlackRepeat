package com.pepekprodakshn.playerlist.ui

import com.pepekprodakshn.playerlist.domain.AddPlayerUseCase
import com.pepekprodakshn.playerlist.domain.GetAllPlayersUseCase
import com.pepekprodakshn.playerlist.domain.ValidateNameUseCase
import com.pepekprodakshn.playerlist.domain.model.ValidationResult
import com.pepekprodakshn.playerlist.ui.model.PlayerUI
import com.pepekprodakshn.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ChoosePlayerViewModel @Inject constructor(
    private val getAllPlayersUseCase: GetAllPlayersUseCase,
    private val addPlayerUseCase: AddPlayerUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
) : BaseViewModel<ChoosePlayerViewState, ChoosePlayerEvent, ChoosePlayerNavigationEvent>(
    initialState = ChoosePlayerViewState(),
) {
    init {
        launch { updateList() }
    }

    override fun onEvent(event: ChoosePlayerEvent) {
        when (event) {
            is ChoosePlayerEvent.OnPlayerClick -> {
                updateState { changePlayerSelection(event.player) }
            }

            is ChoosePlayerEvent.OnNameChanged -> updateState { updateNewPlayerName(event.name) }
            ChoosePlayerEvent.OnAddPlayerClick -> updateState { openNewPlayerBottomSheet() }
            ChoosePlayerEvent.OnNewPlayerBottomSheetClosed -> {
                updateState { closeNewPlayerBottomSheet() }
            }

            ChoosePlayerEvent.OnNewPlayerDoneClick -> onAddPlayerClick()
            ChoosePlayerEvent.OnStartFrameClick -> {
                onNavigationEvent(
                    ChoosePlayerNavigationEvent.OnStartFrameClick(
                        firstPlayerId = viewState.selectedPlayers.first().id,
                        secondPlayerId = viewState.selectedPlayers.last().id,
                    ),
                )
            }

            ChoosePlayerEvent.OnBackPressed -> {
                onNavigationEvent(ChoosePlayerNavigationEvent.OnBackPress)
            }
        }
    }

    private fun onAddPlayerClick() {
        when (validateNameUseCase.execute(viewState.newPlayerName)) {
            ValidationResult.Error -> updateState { showNewNameValidationError() }
            ValidationResult.Success -> addPlayer()
        }
    }

    private fun addPlayer() {
        launch {
            addPlayerUseCase.execute(PlayerUI(1, viewState.newPlayerName))
            updateState { setEmptyNewPlayerState() }
            updateList()
        }
    }

    private suspend fun updateList() {
        val players = getAllPlayersUseCase.execute()
        updateState { updatePlayers(players) }
    }
}