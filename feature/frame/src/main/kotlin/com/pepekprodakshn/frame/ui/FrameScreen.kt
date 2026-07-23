package com.pepekprodakshn.frame.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.pepekprodakshn.designsystem.isPortrait
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.ButtonDescription
import com.pepekprodakshn.designsystem.widgets.ScreenPreviews
import com.pepekprodakshn.designsystem.widgets.SpacerWidth
import com.pepekprodakshn.designsystem.widgets.ThreeButtonsDialog
import com.pepekprodakshn.frame.R
import com.pepekprodakshn.frame.ui.model.FrameOptionUI
import com.pepekprodakshn.frame.ui.widgets.BallsWidget
import com.pepekprodakshn.frame.ui.widgets.FrameInfoWidget
import com.pepekprodakshn.frame.ui.widgets.FrameOptions
import com.pepekprodakshn.frame.ui.widgets.PlayersCounters

@Composable
internal fun FrameScreen(
    viewModel: FrameViewModel = hiltViewModel(),
    navigationHandler: (FrameNavigationEvent) -> Unit,
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(Unit) { viewModel.navigationEvent.collect(navigationHandler) }

    BackHandler(state.isBackHandlerEnabled) { viewModel.onEvent(FrameEvent.OnBackPressed) }

    FrameScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
    )
}

@Composable
private fun FrameScreenContent(state: FrameUiState, onEvent: (FrameEvent) -> Unit) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = MaterialTheme.colorScheme.surface,
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if (isPortrait()) {
                    BallsWidget(
                        safeContentPadding = paddingValues,
                        ballsState = state.tableState.ballState,
                        onClick = { onEvent(FrameEvent.OnBallClick(it)) },
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        FrameInfoWidget(
                            modifier = Modifier.weight(1f, fill = false),
                            safeContentPadding = paddingValues,
                            info = state.tableState.frameInfoUI,
                        )

                        SpacerWidth(16.dp)

                        FrameOptions(
                            modifier = Modifier,
                            onEvent = onEvent,
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        BallsWidget(
                            modifier = Modifier
                                .padding(top = paddingValues.calculateTopPadding()),
                            safeContentPadding = paddingValues,
                            ballsState = state.tableState.ballState,
                            onClick = { onEvent(FrameEvent.OnBallClick(it)) },
                        )

                        FrameInfoWidget(
                            modifier = Modifier.weight(1f, fill = false),
                            safeContentPadding = paddingValues,
                            info = state.tableState.frameInfoUI,
                        )

                        FrameOptions(
                            modifier = Modifier
                                .padding(top = paddingValues.calculateTopPadding()),
                            safeContentPadding = paddingValues,
                            onEvent = onEvent,
                        )
                    }
                }

                PlayersCounters(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                    firstPlayerUI = state.firstPlayerUI,
                    firstPlayerPoints = state.tableState.firstPlayerPoints,
                    secondPlayerUI = state.secondPlayerUI,
                    secondPlayerPoints = state.tableState.secondPlayerPoints,
                    isFirstPlayerSelected = state.tableState.isFirstPlayerSelected,
                    breakUI = state.tableState.breakUI,
                    previousBreakUI = state.previousBreakUI,
                    safeContentPadding = paddingValues,
                    onClick = { onEvent(FrameEvent.OnSelectPlayer(it)) },
                )
            }
        }

        if (state.showFoulBottomSheet) {
            FoulDialog(
                foulUI = state.foulUI,
                onEvent = onEvent,
            )
        }

        if (state.showAddRemoveDialog) {
            AddRemoveRedDialog(
                state = state.addRemoveDialogState,
                onEvent = onEvent,
            )
        }

        if (state.showOptionsBottomSheet) {
            FrameOptionsBottomSheet(
                options = FrameOptionUI.entries.drop(state.optionElementsOnScreen),
                onEvent = onEvent,
            )
        }

        if (state.showRestartFrameConfirmationDialog) {
            ThreeButtonsDialog(
                title = stringResource(id = R.string.frame_restart_frame_dialog_title),
                description = stringResource(id = R.string.frame_restart_frame_dialog_description),
                okButtonDescription = ButtonDescription(
                    text = stringResource(id = R.string.frame_finish_frame_dialog_yes),
                    onClick = { onEvent(FrameEvent.OnRestartConfirm) },
                ),
                noButtonDescription = ButtonDescription(
                    text = stringResource(id = R.string.frame_finish_frame_dialog_no),
                    onClick = { onEvent(FrameEvent.OnRestartFrameConfirmationClosed) },
                ),
                onDismissRequest = { onEvent(FrameEvent.OnRestartFrameConfirmationClosed) },
            )
        }
    }
}

@ScreenPreviews
@Composable
private fun FrameScreenPreview() {
    FrameScreenPreviewContent()
}

@Composable
private fun FrameScreenPreviewContent() {
    RedBlackRepeatTheme {
        FrameScreenContent(state = frameUiStateMock) {}
    }
}
