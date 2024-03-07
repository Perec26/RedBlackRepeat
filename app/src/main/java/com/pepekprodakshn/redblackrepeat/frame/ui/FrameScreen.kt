package com.pepekprodakshn.redblackrepeat.frame.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pepekprodakshn.redblackrepeat.base.ui.LANDSCAPE_DEVICE
import com.pepekprodakshn.redblackrepeat.frame.ui.model.FrameOptionUI
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.BallsWidget
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.FrameOptions
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.PlayersCounters
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun FrameScreen(
    viewModel: FrameViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    FrameScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun FrameScreenContent(
    state: FrameUiState,
    onEvent: (FrameEvent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                BallsWidget(
                    modifier = Modifier.padding(top = 16.dp),
                    ballsState = state.tableState.ballState,
                    onClick = { onEvent(FrameEvent.OnBallClick(it)) }
                )

                FrameOptions(
                    modifier = Modifier.padding(top = 16.dp),
                    onEvent = onEvent,
                )
            }


            PlayersCounters(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .padding(bottom = 16.dp),
                firstPlayerUI = state.firstPlayerUI,
                firstPlayerPoints = state.tableState.firstPlayerPoints,
                secondPlayerUI = state.secondPlayerUI,
                secondPlayerPoints = state.tableState.secondPlayerPoints,
                isFirstPlayerSelected = state.tableState.isFirstPlayerSelected,
                breakUI = state.tableState.breakUI,
                previousBreakUI = state.previousBreakUI,
                onCLick = { onEvent(FrameEvent.OnSelectPlayer(it)) }
            )
        }
    }

    if (state.showFoulBottomSheet) {
        FoulDialog(
            foulUI = state.foulUI,
            onEvent = onEvent
        )
    }

    if (state.showAddRemoveDialog) {
        AddRemoveRedDialog(
            state = state.addRemoveDialogState,
            onEvent = onEvent
        )
    }

    if (state.showOptionsBottomSheet) {
        FrameOptionsBottomSheet(
            options = FrameOptionUI.values().toList().drop(state.optionElementsOnScreen),
            onEvent = onEvent
        )
    }
}

@Preview(
    showBackground = true,
    device = LANDSCAPE_DEVICE,
    showSystemUi = true,
)
@Composable
private fun FrameScreenPreview() {
    FrameScreenPreviewContent()
}

@Preview(
    showBackground = true,
    device = LANDSCAPE_DEVICE,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun FrameScreenPreviewDark() {
    FrameScreenPreviewContent()
}

@Composable
private fun FrameScreenPreviewContent() {
    RedBlackRepeatTheme {
        FrameScreenContent(state = frameUiStateMock) {}
    }
}