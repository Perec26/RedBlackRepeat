package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.isPortrait
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.SpacerHeight
import com.pepekprodakshn.designsystem.widgets.SpacerWidth
import com.pepekprodakshn.designsystem.widgets.WidgetPreviews
import com.pepekprodakshn.frame.ui.PlayerUI
import com.pepekprodakshn.frame.ui.breakMock
import com.pepekprodakshn.frame.ui.firstPlayerUIMock
import com.pepekprodakshn.frame.ui.model.BreakUI
import com.pepekprodakshn.frame.ui.secondPlayerUIMock

@Composable
internal fun PlayersCounters(
    modifier: Modifier = Modifier,
    firstPlayerUI: PlayerUI,
    firstPlayerPoints: Int,
    secondPlayerUI: PlayerUI,
    secondPlayerPoints: Int,
    isFirstPlayerSelected: Boolean,
    breakUI: BreakUI? = null,
    previousBreakUI: BreakUI? = null,
    safeContentPadding: PaddingValues = PaddingValues(),
    onClick: (Boolean) -> Unit,
) {
    if (isPortrait()) {
        PlayersCountersPortrait(
            modifier = modifier,
            firstPlayerUI = firstPlayerUI,
            firstPlayerPoints = firstPlayerPoints,
            secondPlayerUI = secondPlayerUI,
            secondPlayerPoints = secondPlayerPoints,
            isFirstPlayerSelected = isFirstPlayerSelected,
            breakUI = breakUI,
            previousBreakUI = previousBreakUI,
            onClick = onClick,
        )
    } else {
        PlayersCountersLandscape(
            modifier = modifier,
            firstPlayerUI = firstPlayerUI,
            firstPlayerPoints = firstPlayerPoints,
            secondPlayerUI = secondPlayerUI,
            secondPlayerPoints = secondPlayerPoints,
            isFirstPlayerSelected = isFirstPlayerSelected,
            breakUI = breakUI,
            previousBreakUI = previousBreakUI,
            safeContentPadding = safeContentPadding,
            onClick = onClick,
        )
    }
}

@Composable
private fun PlayersCountersPortrait(
    modifier: Modifier = Modifier,
    firstPlayerUI: PlayerUI,
    firstPlayerPoints: Int,
    secondPlayerUI: PlayerUI,
    secondPlayerPoints: Int,
    isFirstPlayerSelected: Boolean,
    breakUI: BreakUI? = null,
    previousBreakUI: BreakUI? = null,
    onClick: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BreakWidget(
                modifier = Modifier.alpha(0f),
                breakUI = breakUI,
                isRight = true,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                AnimatedBreakWidget(
                    breakUI = breakUI ?: previousBreakUI,
                    isFirst = true,
                    isVisible = isFirstPlayerSelected && breakUI != null,
                )
                SpacerWidth(width = 16.dp)

                AnimatedBreakWidget(
                    breakUI = breakUI ?: previousBreakUI,
                    isFirst = false,
                    isVisible = !isFirstPlayerSelected && breakUI != null,
                )
            }
        }

        SpacerHeight(height = 16.dp)

        PlayerLabel(
            modifier = Modifier.padding(end = 16.dp),
            isActive = isFirstPlayerSelected,
            name = firstPlayerUI.name,
            points = firstPlayerPoints,
            difference = firstPlayerPoints - secondPlayerPoints,
            isFirst = true,
            onClick = { onClick(true) },
        )

        SpacerHeight(height = 8.dp)

        PlayerLabel(
            modifier = Modifier.padding(start = 16.dp),
            isActive = !isFirstPlayerSelected,
            name = secondPlayerUI.name,
            points = secondPlayerPoints,
            difference = secondPlayerPoints - firstPlayerPoints,
            isFirst = false,
            onClick = { onClick(false) },
        )
    }
}

@Composable
private fun PlayersCountersLandscape(
    modifier: Modifier = Modifier,
    firstPlayerUI: PlayerUI,
    firstPlayerPoints: Int,
    secondPlayerUI: PlayerUI,
    secondPlayerPoints: Int,
    isFirstPlayerSelected: Boolean,
    breakUI: BreakUI? = null,
    previousBreakUI: BreakUI? = null,
    safeContentPadding: PaddingValues,
    onClick: (Boolean) -> Unit,
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
            safeContentPadding = safeContentPadding,
            onClick = { onClick(true) },
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
            safeContentPadding = safeContentPadding,
            onClick = { onClick(false) },
        )
    }
}

@WidgetPreviews
@Composable
private fun PlayersCountersFirstPlayerSelectedPreview() {
    RedBlackRepeatTheme {
        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            isFirstPlayerSelected = true,
        ) {}
    }
}

@WidgetPreviews
@Composable
private fun PlayersCountersSecondPlayerSelectedPreview() {
    RedBlackRepeatTheme {
        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            isFirstPlayerSelected = false,
            onClick = {},
        )
    }
}

@WidgetPreviews
@Composable
private fun PlayersCountersSecondPlayerWinningPreview() {
    RedBlackRepeatTheme {
        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 77,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 102,
            isFirstPlayerSelected = false,
        ) {}
    }
}

@WidgetPreviews
@Composable
private fun PlayersCountersFirstPlayerBreakPreview() {
    RedBlackRepeatTheme {
        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            breakUI = breakMock,
            isFirstPlayerSelected = true,
        ) {}
    }
}

@WidgetPreviews
@Composable
private fun PlayersCountersSecondPlayerBreakPreview() {
    RedBlackRepeatTheme {
        PlayersCounters(
            firstPlayerUI = firstPlayerUIMock,
            firstPlayerPoints = 102,
            secondPlayerUI = secondPlayerUIMock,
            secondPlayerPoints = 77,
            breakUI = breakMock,
            isFirstPlayerSelected = false,
        ) {}
    }
}
