package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import androidx.lifecycle.viewModelScope
import com.pepekprodakshn.redblackrepeat.base.ui.BaseViewModel
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.AddPlayerUseCase
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.GetAllPlayersUseCase
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.ValidateNameUseCase
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.model.ValidationResult
import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI
import com.pepekprodakshn.redblackrepeat.navigation.Destinations
import com.pepekprodakshn.redblackrepeat.navigation.RBRNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChoosePlayerViewModel @Inject constructor(
    private val getAllPlayersUseCase: GetAllPlayersUseCase,
    private val addPlayerUseCase: AddPlayerUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val navController: RBRNavController,
) : BaseViewModel<ChoosePlayerViewState, ChoosePlayerEvent>(
    initialState = ChoosePlayerViewState()
) {
    init {
        viewModelScope.launch { updateList() }
    }

    override fun onEvent(event: ChoosePlayerEvent) {
        when (event) {
            ChoosePlayerEvent.OnBackPressed -> {}
            ChoosePlayerEvent.OnAddPlayerClick -> updateState { toShowNewPlayerBottomSheet(true) }
            is ChoosePlayerEvent.OnPlayerClick -> updateState { toSelectPlayerChange(event.player) }
            is ChoosePlayerEvent.OnNameChanged -> updateState { toUpdateNewPlayerName(event.name) }
            ChoosePlayerEvent.OnNewPlayerBottomSheetClosed -> {
                updateState { toShowNewPlayerBottomSheet(false) }
            }

            ChoosePlayerEvent.OnNewPlayerDoneClick -> onAddPlayerClick()
            ChoosePlayerEvent.OnStartMatchClick -> {
                navController.navigateTo(
                    destination = Destinations.Frame,
                    viewState.players.first().id,
                    viewState.players.last().id
                )
            }
        }
    }

    private fun onAddPlayerClick() {
        when (validateNameUseCase.execute(viewState.newPlayerName)) {
            ValidationResult.Error -> updateState { toNewNameValidationError() }
            ValidationResult.Success -> addPlayer()
        }
    }

    private fun addPlayer() {
        viewModelScope.launch {
            addPlayerUseCase.execute(PlayerUI(1, viewState.newPlayerName))
            updateState { toEmptyNewPlayer() }
            updateList()
        }
    }

    private suspend fun updateList() {
        val players = getAllPlayersUseCase.execute()
        updateState { toInitial(players) }
    }
}