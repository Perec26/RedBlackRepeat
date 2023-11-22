package com.pepekprodakshn.redblackrepeat.frame.ui

import androidx.lifecycle.viewModelScope
import com.pepekprodakshn.redblackrepeat.base.ui.BaseViewModel
import com.pepekprodakshn.redblackrepeat.frame.domain.GetPlayerUseCase
import com.pepekprodakshn.redblackrepeat.navigation.FIRST_PLAYER_ID
import com.pepekprodakshn.redblackrepeat.navigation.RBRNavController
import com.pepekprodakshn.redblackrepeat.navigation.SECOND_PLAYER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FrameViewModel @Inject constructor(
    private val navController: RBRNavController,
    private val getPlayerUseCase: GetPlayerUseCase,
) : BaseViewModel<FrameUiState, FrameEvent>(
    initialState = FrameUiState()
) {

    init {
        val firstPlayerId = navController.getIntArg(FIRST_PLAYER_ID) ?: error("Can't find argument")
        val secondPlayerId =
            navController.getIntArg(SECOND_PLAYER_ID) ?: error("Can't find argument")
        getPlayers(firstPlayerId, secondPlayerId)
    }

    private fun getPlayers(firstPlayerId: Int, secondPlayerId: Int) {
        viewModelScope.launch {
            val firstPlayer = getPlayerUseCase.execute(firstPlayerId)
            val secondPlayer = getPlayerUseCase.execute(secondPlayerId)
            updateState { initPlayers(firstPlayer, secondPlayer) }
        }
    }

    override fun onEvent(event: FrameEvent) = when (event) {
        is FrameEvent.OnBallClick -> updateState { onBallPotted(event.ballUI) }
        is FrameEvent.OnFoulClick -> onFoulCLick(event.foul)
        is FrameEvent.OnRemoveClick -> onRemoveCLick(event.remove)
        is FrameEvent.OnSelectPlayer -> updateState { selectPlayer(event.selectedPlayer) }
    }

    private fun onFoulCLick(foul: Int) {
        when (viewState.selectedPlayer) {
            SelectedPlayer.FIRST -> updateState { addSecondPlayerPoints(foul) }
            SelectedPlayer.SECOND -> updateState { addFirstPlayerPoints(foul) }
        }
    }

    private fun onRemoveCLick(remove: Int) {
        when (viewState.selectedPlayer) {
            SelectedPlayer.FIRST -> updateState { addFirstPlayerPoints(remove) }
            SelectedPlayer.SECOND -> updateState { addSecondPlayerPoints(remove) }
        }
    }
}