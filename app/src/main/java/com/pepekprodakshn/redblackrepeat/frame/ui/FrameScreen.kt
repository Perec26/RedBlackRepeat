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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerWidth
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.BallsWidget
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.FoulsWidget
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.PlayersCounters
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun FrameScreen(
    viewModel: FrameViewModel = viewModel(),
    onNavigation: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BallsWidget(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f),
                onClick = viewModel::onBallClick
            )

            SpacerWidth(width = 136.dp)

            FoulsWidget(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .weight(1f),
                onFoulClick = viewModel::onFoulCLick,
                onRemoveClick = viewModel::onRemoveCLick,
            )
        }

        SpacerHeight(height = 0.dp, modifier = Modifier.weight(1f))
        PlayersCounters(
            firstPlayerVO = state.firstPlayerVO,
            firstPlayerPoints = state.firstPlayerPoints,
            secondPlayerVO = state.secondPlayerVO,
            secondPlayerPoints = state.secondPlayerPoints,
            selectedPlayer = state.selectedPlayer,
            onCLick = viewModel::selectPlayer
        )
        SpacerHeight(height = 16.dp)
    }
}

@Preview(showBackground = true, device = AUTOMOTIVE_1024p, showSystemUi = true)
@Composable
fun FrameScreenPreview() {
    RedBlackRepeatTheme {
        FrameScreen() {}
    }
}

@Preview(
    showBackground = true,
    device = AUTOMOTIVE_1024p,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FrameScreenPreviewDark() {
    RedBlackRepeatTheme {
        FrameScreen() {}
    }
}