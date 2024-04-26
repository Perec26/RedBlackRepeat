package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import com.pepekprodakshn.frame.ui.PlayerUI
import com.pepekprodakshn.frame.ui.navigation.FrameDestinations
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.AddPlayerUseCase
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.GetAllPlayersUseCase
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.ValidateNameUseCase
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.model.ValidationResult
import com.pepekprodakshn.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChoosePlayerViewModel @Inject constructor(
    private val getAllPlayersUseCase: GetAllPlayersUseCase,
    private val addPlayerUseCase: AddPlayerUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val navController: com.pepekprodakshn.navigation.RBRNavController,
) : BaseViewModel<ChoosePlayerViewState, ChoosePlayerEvent>(
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
            ChoosePlayerEvent.OnBackPressed -> navController.navigateUp()
            ChoosePlayerEvent.OnAddPlayerClick -> updateState { openNewPlayerBottomSheet() }
            ChoosePlayerEvent.OnNewPlayerBottomSheetClosed -> {
                updateState { closeNewPlayerBottomSheet() }
            }

            ChoosePlayerEvent.OnNewPlayerDoneClick -> onAddPlayerClick()
            ChoosePlayerEvent.OnStartMatchClick -> onStartMatchClick()
        }
    }

    private fun onStartMatchClick() {
        navController.navigateTo(
            destination = FrameDestinations.Frame,
            viewState.selectedPlayers.first().id,
            viewState.selectedPlayers.last().id,
        )
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