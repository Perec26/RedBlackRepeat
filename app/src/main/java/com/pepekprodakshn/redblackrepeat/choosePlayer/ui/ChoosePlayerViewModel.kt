package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import androidx.lifecycle.viewModelScope
import com.pepekprodakshn.redblackrepeat.base.ui.BaseViewModel
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.AddPlayerUseCase
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.GetAllPlayersUseCase
import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChoosePlayerViewModel @Inject constructor(
    private val getAllPlayersUseCase: GetAllPlayersUseCase,
    private val addPlayerUseCase: AddPlayerUseCase,
) : BaseViewModel<ChoosePlayerViewState, ChoosePlayerEvent>(
    initialState = ChoosePlayerViewState()
) {
    init {
        viewModelScope.launch { updateList() }
    }

    override fun onEvent(event: ChoosePlayerEvent) {
        when (event) {
            ChoosePlayerEvent.OnBackPressed -> {}
            ChoosePlayerEvent.OnAddPlayerClick -> onAddPlayerClick()
            is ChoosePlayerEvent.OnPlayerClick -> updateState { toSelectPlayerChange(event.player) }
        }
    }

    private fun onAddPlayerClick() {
        viewModelScope.launch {
            addPlayerUseCase.execute(PlayerUI(1, "DDF"))
            updateList()
        }
    }

    private suspend fun updateList() {
        val players = getAllPlayersUseCase.execute()
        updateState { toInitial(players) }
    }
}