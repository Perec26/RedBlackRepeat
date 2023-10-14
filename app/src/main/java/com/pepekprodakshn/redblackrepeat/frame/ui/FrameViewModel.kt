package com.pepekprodakshn.redblackrepeat.frame.ui

import com.pepekprodakshn.redblackrepeat.base.ui.BaseViewModel
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallVO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FrameViewModel @Inject constructor() : BaseViewModel<FrameUiState>(FrameUiState()) {

    fun selectPlayer(selectedPlayer: SelectedPlayer) {
        _state.value = state.value.copy(selectedPlayer = selectedPlayer)
    }



    fun onBallClick(ballVO: BallVO){
        when(state.value.selectedPlayer){
            SelectedPlayer.FIRST -> _state.value = state.value.addFirstPlayerPoints(ballVO.value)
            SelectedPlayer.SECOND -> _state.value = state.value.addSecondPlayerPoints(ballVO.value)
        }
    }

    fun onFoulCLick(foul: Int){
        when(state.value.selectedPlayer){
            SelectedPlayer.FIRST -> _state.value = state.value.addSecondPlayerPoints(foul)
            SelectedPlayer.SECOND -> _state.value = state.value.addFirstPlayerPoints(foul)
        }
    }

    fun onRemoveCLick(remove: Int){
        when(state.value.selectedPlayer){
            SelectedPlayer.FIRST -> _state.value = state.value.addFirstPlayerPoints(remove)
            SelectedPlayer.SECOND -> _state.value = state.value.addSecondPlayerPoints(remove)
        }
    }
}