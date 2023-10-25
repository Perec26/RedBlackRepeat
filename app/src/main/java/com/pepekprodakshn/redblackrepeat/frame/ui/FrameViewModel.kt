package com.pepekprodakshn.redblackrepeat.frame.ui

import com.pepekprodakshn.redblackrepeat.base.ui.BaseViewModel
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FrameViewModel @Inject constructor() : BaseViewModel<FrameUiState, FrameEvent>(
    initialState = FrameUiState()
) {
    override fun onEvent(event: FrameEvent) = when (event) {
        is FrameEvent.OnBallClick -> onBallClick(event.ballVO)
        is FrameEvent.OnFoulClick -> onFoulCLick(event.foul)
        is FrameEvent.OnRemoveClick -> onRemoveCLick(event.remove)
        is FrameEvent.OnSelectPlayer -> updateState { selectPlayer(selectedPlayer) }
    }

    private fun onBallClick(ballVO: BallVO) {
        when (viewState.selectedPlayer) {
            SelectedPlayer.FIRST -> updateState { addFirstPlayerPoints(ballVO.value) }
            SelectedPlayer.SECOND -> updateState { addSecondPlayerPoints(ballVO.value) }
        }
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