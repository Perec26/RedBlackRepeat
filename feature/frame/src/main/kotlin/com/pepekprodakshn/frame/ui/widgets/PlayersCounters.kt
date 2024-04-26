package com.pepekprodakshn.frame.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.LANDSCAPE_DEVICE
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.frame.ui.PlayerUI
import com.pepekprodakshn.frame.ui.breakMock
import com.pepekprodakshn.frame.ui.firstPlayerUIMock
import com.pepekprodakshn.frame.ui.model.BreakUI
import com.pepekprodakshn.frame.ui.secondPlayerUIMock

@Composable
fun PlayersCounters(
    modifier: Modifier = Modifier,
    firstPlayerUI: PlayerUI,
    firstPlayerPoints: Int,
    secondPlayerUI: PlayerUI,
    secondPlayerPoints: Int,
    isFirstPlayerSelected: Boolean,
    breakUI: BreakUI? = null,
    previousBreakUI: BreakUI? = null,
    onCLick: (Boolean) -> Unit,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(36.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        PlayerWidgetWithBreakInfo(
            modifier = Modifier.weight(1f),
            isActive = isFirstPlayerSelected,
            name = firstPlayerUI.name,
            points = firstPlayerPoints,
            difference = firstPlayerPoints - secondPlayerPoints,
            isFirst = true,
            breakUI = breakUI,
            previousBreakUI = previousBreakUI,
            onClick = { onCLick(true) },
        )

        PlayerWidgetWithBreakInfo(
            modifier = Modifier.weight(1f),
            isActive = !isFirstPlayerSelected,
            name = secondPlayerUI.name,
            points = secondPlayerPoints,
            difference = secondPlayerPoints - firstPlayerPoints,
            isFirst = false,
            breakUI = breakUI,
            previousBreakUI = previousBreakUI,
            onClick = { onCLick(false) },
        )
    }
}

@Preview(device = LANDSCAPE_DEVICE)
@Composable
private fun PlayersCountersPreview() {
    RedBlackRepeatTheme {
        PlayersCountersContent()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = LANDSCAPE_DEVICE)
@Composable
private fun PlayersCountersPreviewDark() {
    RedBlackRepeatTheme {
        PlayersCountersContent()
    }
}

@Composable
private fun PlayersCountersContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            isFirstPlayerSelected = true,
        ) {}

        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            isFirstPlayerSelected = false,
        ) {}

        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 77,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 102,
            isFirstPlayerSelected = false,
        ) {}

        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            breakUI = breakMock,
            isFirstPlayerSelected = true,
        ) {}

        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 77,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 102,
            breakUI = breakMock,
            isFirstPlayerSelected = false,
        ) {}
    }
}