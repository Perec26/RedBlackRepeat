package com.pepekprodakshn.redblackrepeat.frame.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.AUTOMOTIVE_1024p
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerWidth
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.BallsWidget
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.FoulsWidget
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
    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BallsWidget(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f),
                onClick = { onEvent(FrameEvent.OnBallClick(it)) }
            )

            SpacerWidth(width = 136.dp)

            FoulsWidget(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f),
                onFoulClick = { onEvent(FrameEvent.OnFoulClick(it)) },
                onRemoveClick = { onEvent(FrameEvent.OnRemoveClick(it)) },
            )
        }

        SpacerHeight(height = 0.dp, modifier = Modifier.weight(1f))
        PlayersCounters(
            firstPlayerUI = state.firstPlayerUI,
            firstPlayerPoints = state.firstPlayerPoints,
            secondPlayerUI = state.secondPlayerUI,
            secondPlayerPoints = state.secondPlayerPoints,
            selectedPlayer = state.selectedPlayer,
            breakUI = state.breakUI,
            onCLick = { onEvent(FrameEvent.OnSelectPlayer(it)) }
        )
        SpacerHeight(height = 16.dp)
    }
}

@Preview(showBackground = true, device = AUTOMOTIVE_1024p, showSystemUi = true)
@Composable
fun FrameScreenPreview() {
    FrameScreenPreviewContent()
}

@Preview(
    showBackground = true,
    device = AUTOMOTIVE_1024p,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FrameScreenPreviewDark() {
    FrameScreenPreviewContent()
}

@Composable
private fun FrameScreenPreviewContent() {
    RedBlackRepeatTheme {
        FrameScreenContent(state = frameUiStateMock) {}
    }
}