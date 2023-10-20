package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerWidth
import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerVO
import com.pepekprodakshn.redblackrepeat.frame.ui.SelectedPlayer
import com.pepekprodakshn.redblackrepeat.frame.ui.firstPlayerVoMock
import com.pepekprodakshn.redblackrepeat.frame.ui.secondPlayerVoMock
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun PlayersCounters(
    firstPlayerVO: PlayerVO,
    firstPlayerPoints: Int,
    secondPlayerVO: PlayerVO,
    secondPlayerPoints: Int,
    selectedPlayer: SelectedPlayer,
    onCLick: (SelectedPlayer) -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PlayerLabel(
            modifier = Modifier.weight(1f),
            isActive = selectedPlayer == SelectedPlayer.FIRST,
            name = firstPlayerVO.name,
            points = firstPlayerPoints,
            difference = firstPlayerPoints - secondPlayerPoints,
            isFirst = true,
            onClick = { onCLick(SelectedPlayer.FIRST) }
        )


        SpacerWidth(width = 36.dp)

        PlayerLabel(
            modifier = Modifier.weight(1f),
            isActive = selectedPlayer == SelectedPlayer.SECOND,
            name = secondPlayerVO.name,
            points = secondPlayerPoints,
            difference = secondPlayerPoints - firstPlayerPoints,
            isFirst = false,
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
    Column {
        PlayersCounters(
            firstPlayerVO = firstPlayerVoMock,
            firstPlayerPoints = 102,
            secondPlayerVO = secondPlayerVoMock,
            secondPlayerPoints = 77,
            selectedPlayer = SelectedPlayer.FIRST,
        ) {}

        SpacerHeight(height = 16.dp)

        PlayersCounters(
            firstPlayerVO = firstPlayerVoMock,
            firstPlayerPoints = 102,
            secondPlayerVO = secondPlayerVoMock,
            secondPlayerPoints = 77,
            selectedPlayer = SelectedPlayer.SECOND,
        ) {}

        SpacerHeight(height = 16.dp)

        PlayersCounters(
            firstPlayerVO = firstPlayerVoMock,
            firstPlayerPoints = 77,
            secondPlayerVO = secondPlayerVoMock,
            secondPlayerPoints = 102,
            selectedPlayer = SelectedPlayer.SECOND,
        ) {}

        SpacerHeight(height = 16.dp)
    }
}
