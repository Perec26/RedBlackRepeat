package com.pepekprodakshn.frame.ui

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.pepekprodakshn.frame.domain.AddRedsUseCase
import com.pepekprodakshn.frame.domain.EndBreakUseCase
import com.pepekprodakshn.frame.domain.FoulUseCase
import com.pepekprodakshn.frame.domain.GetPlayerUseCase
import com.pepekprodakshn.frame.domain.GetTableState
import com.pepekprodakshn.frame.domain.PotBallUseCase
import com.pepekprodakshn.frame.domain.RemoveRedsUseCase
import com.pepekprodakshn.frame.domain.RestartUseCase
import com.pepekprodakshn.frame.domain.UndoUseCase
import com.pepekprodakshn.frame.ui.model.BallUI
import com.pepekprodakshn.frame.ui.navigation.Frame
import com.pepekprodakshn.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class FrameViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPlayerUseCase: GetPlayerUseCase,
    private val potBallUseCase: PotBallUseCase,
    private val endBreakUseCase: EndBreakUseCase,
    private val getTableState: GetTableState,
    private val foulUseCase: FoulUseCase,
    private val undoUseCase: UndoUseCase,
    private val addRedsUseCase: AddRedsUseCase,
    private val removeRedsUseCase: RemoveRedsUseCase,
    private val restartUseCase: RestartUseCase,
) : BaseViewModel<FrameUiState, FrameEvent, FrameNavigationEvent>(
    initialState = FrameUiState(),
) {

    init {
        val route = savedStateHandle.toRoute<Frame>()
        val firstPlayerId = route.firstPlayerId
        val secondPlayerId = route.secondPlayerId
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
            FrameEvent.OnAddRedsDialogClick -> onAddRedsDialogClick()
            FrameEvent.OnRemoveRedsDialogClick -> onRemoveRedsDialogClick()
            FrameEvent.OnRestartClick -> onRestartClick()
            is FrameEvent.OnMoreClick -> updateState { showOptionsBottomSheet() }
            is FrameEvent.OnOptionsElementsCounted -> updateState { setOptionElements(event.count) }
            FrameEvent.OnFrameOptionsBottomSheetClose -> updateState { hideOptionsBottomSheet() }
            FrameEvent.OnFinishClick -> onFinishClick()
            FrameEvent.OnRestartConfirm -> onRestartConfirm()
            FrameEvent.OnRestartFrameConfirmationClosed -> {
                updateState { hideRestartFrameConfirmationDialog() }
            }

            FrameEvent.OnBackPressed -> onNavigationEvent(FrameNavigationEvent.OnBackPressed)
        }
    }

    private fun onRestartClick() {
        updateState { hideOptionsBottomSheet() }
        updateState { showRestartFrameConfirmationDialog() }
    }

    private fun onRestartConfirm() {
        launch {
            updateState { hideRestartFrameConfirmationDialog() }
            restartUseCase.execute()
            updateTableState()
        }
    }

    private fun onFinishClick() {
        updateState { hideOptionsBottomSheet() }
        onNavigationEvent(FrameNavigationEvent.OnBackPressed)
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