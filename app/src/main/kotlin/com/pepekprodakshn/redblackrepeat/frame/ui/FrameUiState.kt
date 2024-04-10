package com.pepekprodakshn.redblackrepeat.frame.ui

import androidx.compose.runtime.Stable
import com.pepekprodakshn.redblackrepeat.frame.ui.model.AddRemoveDialogState
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BreakUI
import com.pepekprodakshn.redblackrepeat.frame.ui.model.FoulUI
import com.pepekprodakshn.redblackrepeat.frame.ui.model.LOWEST_FOUL_VALUE
import com.pepekprodakshn.redblackrepeat.frame.ui.model.TableStateUI

@Stable
data class FrameUiState(
    val firstPlayerUI: PlayerUI = PlayerUI(0, ""),
    val secondPlayerUI: PlayerUI = PlayerUI(0, ""),
    val tableState: TableStateUI = TableStateUI(),
    val previousBreakUI: BreakUI? = null,
    val firstPlayerPoints: Int = 0,
    val secondPlayerPoints: Int = 0,
    val foulUI: FoulUI = FoulUI(),
    val addRemoveDialogState: AddRemoveDialogState = AddRemoveDialogState(),
    val showFoulBottomSheet: Boolean = false,
    val showAddRemoveDialog: Boolean = false,
    val showOptionsBottomSheet: Boolean = false,
    val showFinishFrameConfirmationDialog: Boolean = false,
    val showRestartFrameConfirmationDialog: Boolean = false,
    val optionElementsOnScreen: Int = 0,
) {

    val isBackHandlerEnabled = !(
        showFoulBottomSheet &&
            showAddRemoveDialog &&
            showOptionsBottomSheet &&
            showFinishFrameConfirmationDialog
        )

    fun initPlayers(
        firstPlayerUI: PlayerUI,
        secondPlayerUI: PlayerUI,
    ) = copy(
        firstPlayerUI = firstPlayerUI,
        secondPlayerUI = secondPlayerUI,
    )

    fun updateTableState(tableState: TableStateUI) = copy(
        tableState = tableState,
        previousBreakUI = this.tableState.breakUI,
    )

    fun openFoulBottomSheet() = copy(
        showFoulBottomSheet = true,
        showOptionsBottomSheet = false,
        foulUI = FoulUI(
            showRemoveReds = tableState.ballState.redsCount > 0,
            lowestBallValue = maxOf(tableState.ballState.lowestPriceBall.value, LOWEST_FOUL_VALUE),
        ),
    )

    fun closeFoulBottomSheet() = copy(showFoulBottomSheet = false)

    fun setFoulPoints(points: Int) = copy(foulUI = foulUI.copy(points = points))

    fun setFoulIsMiss() = copy(
        foulUI = foulUI.copy(
            isMiss = !foulUI.isMiss,
            isFreeBall = false,
        ),
    )

    fun setFoulFreeBall() = copy(
        foulUI = foulUI.copy(
            isFreeBall = !foulUI.isFreeBall,
            isMiss = false,
        ),
    )

    fun addFoulRedBall() = copy(
        foulUI = foulUI.copy(
            removeReds = foulUI.removeReds + 1,
            canAddReds = foulUI.removeReds + 1 < tableState.ballState.redsCount,
        ),
    )

    fun removeFoulRedBall() = copy(
        foulUI = foulUI.copy(
            removeReds = foulUI.removeReds - 1,
            canAddReds = foulUI.removeReds - 1 < tableState.ballState.redsCount,
        ),
    )

    fun openAddRedsDialog() = copy(
        addRemoveDialogState = AddRemoveDialogState(
            redsOnTable = tableState.ballState.redsCount,
        ),
        showAddRemoveDialog = true,
        showOptionsBottomSheet = false,
    )

    fun openRemoveRedsDialog() = copy(
        addRemoveDialogState = AddRemoveDialogState(
            redsOnTable = tableState.ballState.redsCount,
            isAdd = false,
        ),
        showAddRemoveDialog = true,
        showOptionsBottomSheet = false,
    )

    fun closeAddRemoveRedsDialog() = copy(
        showAddRemoveDialog = false,
    )

    fun plusAddRemoveRedsDialog() = copy(
        addRemoveDialogState = addRemoveDialogState.copy(
            redsCount = addRemoveDialogState.redsCount + 1,
        ),
    )

    fun minusAddRemoveRedsDialog() = copy(
        addRemoveDialogState = addRemoveDialogState.copy(
            redsCount = addRemoveDialogState.redsCount - 1,
        ),
    )

    fun setOptionElements(count: Int) = copy(optionElementsOnScreen = count)

    fun showOptionsBottomSheet() = copy(showOptionsBottomSheet = true)

    fun hideOptionsBottomSheet() = copy(showOptionsBottomSheet = false)

    fun showFinishFrameConfirmationDialog() = copy(showFinishFrameConfirmationDialog = true)

    fun hideFinishFrameConfirmationDialog() = copy(showFinishFrameConfirmationDialog = false)

    fun showRestartFrameConfirmationDialog() = copy(showRestartFrameConfirmationDialog = true)

    fun hideRestartFrameConfirmationDialog() = copy(showRestartFrameConfirmationDialog = false)
}

data class PlayerUI(
    val id: Int,
    val name: String,
)