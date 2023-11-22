package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerWidth
import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI
import com.pepekprodakshn.redblackrepeat.frame.ui.SelectedPlayer
import com.pepekprodakshn.redblackrepeat.frame.ui.breakMock
import com.pepekprodakshn.redblackrepeat.frame.ui.firstPlayerUIMock
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BreakUI
import com.pepekprodakshn.redblackrepeat.frame.ui.secondPlayerUIMock
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun PlayersCounters(
    firstPlayerUI: PlayerUI,
    firstPlayerPoints: Int,
    secondPlayerUI: PlayerUI,
    secondPlayerPoints: Int,
    selectedPlayer: SelectedPlayer,
    breakUI: BreakUI? = null,
    onCLick: (SelectedPlayer) -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ) {
        PlayerWidgetWithBreakInfo(
            modifier = Modifier.weight(1f),
            isActive = selectedPlayer == SelectedPlayer.FIRST,
            name = firstPlayerUI.name,
            points = firstPlayerPoints,
            difference = firstPlayerPoints - secondPlayerPoints,
            isFirst = true,
            breakUI = breakUI.takeIf { selectedPlayer == SelectedPlayer.FIRST },
            onClick = { onCLick(SelectedPlayer.FIRST) }
        )

        SpacerWidth(width = 36.dp)

        PlayerWidgetWithBreakInfo(
            modifier = Modifier.weight(1f),
            isActive = selectedPlayer == SelectedPlayer.SECOND,
            name = secondPlayerUI.name,
            points = secondPlayerPoints,
            difference = secondPlayerPoints - firstPlayerPoints,
            isFirst = false,
            breakUI = breakUI.takeIf { selectedPlayer == SelectedPlayer.SECOND },
            onClick = { onCLick(SelectedPlayer.SECOND) }
        )

    }
}

@Preview(device = Devices.AUTOMOTIVE_1024p)
@Composable
private fun PlayersCountersPreview() {
    RedBlackRepeatTheme {
        PlayersCountersContent()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.AUTOMOTIVE_1024p)
@Composable
private fun PlayersCountersPreviewDark() {
    RedBlackRepeatTheme {
        PlayersCountersContent()
    }
}

@Composable
private fun PlayersCountersContent() {
    Column() {
        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            selectedPlayer = SelectedPlayer.FIRST,
        ) {}

        SpacerHeight(height = 16.dp)

        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            selectedPlayer = SelectedPlayer.SECOND,
        ) {}

        SpacerHeight(height = 16.dp)

        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 77,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 102,
            selectedPlayer = SelectedPlayer.SECOND,
        ) {}

        SpacerHeight(height = 16.dp)

        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            breakUI = breakMock,
            selectedPlayer = SelectedPlayer.FIRST,
        ) {}

        SpacerHeight(height = 16.dp)

        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 77,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 102,
            breakUI = breakMock,
            selectedPlayer = SelectedPlayer.SECOND,
        ) {}

        SpacerHeight(height = 16.dp)
    }
}
