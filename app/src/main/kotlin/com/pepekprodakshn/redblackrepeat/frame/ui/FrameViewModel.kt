package com.pepekprodakshn.redblackrepeat.frame.ui

import com.pepekprodakshn.redblackrepeat.base.ui.BaseViewModel
import com.pepekprodakshn.redblackrepeat.frame.domain.AddRedsUseCase
import com.pepekprodakshn.redblackrepeat.frame.domain.EndBreakUseCase
import com.pepekprodakshn.redblackrepeat.frame.domain.FoulUseCase
import com.pepekprodakshn.redblackrepeat.frame.domain.GetPlayerUseCase
import com.pepekprodakshn.redblackrepeat.frame.domain.GetTableState
import com.pepekprodakshn.redblackrepeat.frame.domain.PotBallUseCase
import com.pepekprodakshn.redblackrepeat.frame.domain.RemoveRedsUseCase
import com.pepekprodakshn.redblackrepeat.frame.domain.RestartUseCase
import com.pepekprodakshn.redblackrepeat.frame.domain.UndoUseCase
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallUI
import com.pepekprodakshn.redblackrepeat.navigation.FIRST_PLAYER_ID
import com.pepekprodakshn.redblackrepeat.navigation.RBRNavController
import com.pepekprodakshn.redblackrepeat.navigation.SECOND_PLAYER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FrameViewModel @Inject constructor(
    private val navController: RBRNavController,
    private val getPlayerUseCase: GetPlayerUseCase,
    private val potBallUseCase: PotBallUseCase,
    private val endBreakUseCase: EndBreakUseCase,
    private val getTableState: GetTableState,
    private val foulUseCase: FoulUseCase,
    private val undoUseCase: UndoUseCase,
    private val addRedsUseCase: AddRedsUseCase,
    private val removeRedsUseCase: RemoveRedsUseCase,
    private val restartUseCase: RestartUseCase,
) : BaseViewModel<FrameUiState, FrameEvent>(
    initialState = FrameUiState(),
) {

    init {
        val firstPlayerId =
            navController.getIntArg(FIRST_PLAYER_ID) ?: error("Can't find argument")
        val secondPlayerId =
            navController.getIntArg(SECOND_PLAYER_ID) ?: error("Can't find argument")
        getPlayers(firstPlayerId, secondPlayerId)
    }

    private fun getPlayers(
        firstPlayerId: Int,
        secondPlayerId: Int,
    ) {
        launch {
            val firstPlayer = getPlayerUseCase.execute(firstPlayerId)
            val secondPlayer = getPlayerUseCase.execute(secondPlayerId)
            updateState { initPlayers(firstPlayer, secondPlayer) }
        }
    }

    override fun onEvent(event: FrameEvent) {
        when (event) {
            is FrameEvent.OnBallClick -> onBallClick(event.ballUI)
            is FrameEvent.OnFoulClick -> updateState { openFoulBottomSheet() }
            is FrameEvent.OnSelectPlayer -> onSelectPlayerClick(event.isFirstPlayerSelected)
            FrameEvent.OnFoulBottomSheetClosed -> updateState { closeFoulBottomSheet() }
            is FrameEvent.OnFoulPointClick -> updateState { setFoulPoints(event.points) }
            FrameEvent.OnFoulIsMissClick -> updateState { setFoulIsMiss() }
            FrameEvent.OnFoulIsFreeBallClick -> updateState { setFoulFreeBall() }
            FrameEvent.OnFoulAddRedsClick -> updateState { addFoulRedBall() }
            FrameEvent.OnFoulRemoveRedsClick -> updateState { removeFoulRedBall() }
            FrameEvent.OnFoulConfirmClick -> onFoulConfirm()
            FrameEvent.OnUndoClick -> onUndoClick()
            FrameEvent.OnAddRedsClick -> updateState { openAddRedsDialog() }
            FrameEvent.OnRemoveRedsClick -> updateState { openRemoveRedsDialog() }
            FrameEvent.OnAddRemoveRedsDialogClose -> updateState { closeAddRemoveRedsDialog() }
            FrameEvent.OnAddRemoveRedsPlusClick -> updateState { plusAddRemoveRedsDialog() }
            FrameEvent.OnAddRemoveRedsMinusClick -> updateState { minusAddRemoveRedsDialog() }
            is FrameEvent.OnAddRedsDialogClick -> onAddRedsDialogClick()
            is FrameEvent.OnRemoveRedsDialogClick -> onRemoveRedsDialogClick()
            FrameEvent.OnRestartClick -> onRestartClick()
            is FrameEvent.OnMoreClick -> updateState { showOptionsBottomSheet() }
            is FrameEvent.OnOptionsElementsCounted -> updateState { setOptionElements(event.count) }
            FrameEvent.OnFrameOptionsBottomSheetClose -> updateState { hideOptionsBottomSheet() }
        }
    }

    private fun onRestartClick() {
        launch {
            updateState { hideOptionsBottomSheet() }
            restartUseCase.execute()
            updateTableState()
        }
    }

    private fun onAddRedsDialogClick() {
        launch {
            addRedsUseCase.execute(viewState.addRemoveDialogState.redsCount)
            updateState { closeAddRemoveRedsDialog() }
            updateTableState()
        }
    }

    private fun onRemoveRedsDialogClick() {
        launch {
            removeRedsUseCase.execute(viewState.addRemoveDialogState.redsCount)
            updateState { closeAddRemoveRedsDialog() }
            updateTableState()
        }
    }

    private fun onUndoClick() {
        launch {
            undoUseCase.execute()
            updateState { hideOptionsBottomSheet() }
            updateTableState()
        }
    }

    private fun onFoulConfirm() {
        launch {
            foulUseCase.execute(viewState.foulUI)
            updateState { closeFoulBottomSheet() }
            updateTableState()
        }
    }

    private fun onBallClick(ballUI: BallUI) {
        launch {
            potBallUseCase.execute(ballUI)
            updateTableState()
        }
    }

    private fun onSelectPlayerClick(isFirstPlayerSelected: Boolean) {
        launch {
            if (isFirstPlayerSelected != viewState.tableState.isFirstPlayerSelected) {
                endBreakUseCase.execute()
            }
            updateTableState()
        }
    }

    private fun updateTableState() = updateState { updateTableState(getTableState.execute()) }
}