package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.WidgetPreviews
import com.pepekprodakshn.frame.ui.breakMock
import com.pepekprodakshn.frame.ui.model.BreakUI

@Composable
internal fun PlayerWidgetWithBreakInfo(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    name: String = "Ronnie O'Sullivan",
    points: Int = 102,
    difference: Int = 0,
    isFirst: Boolean = true,
    breakUI: BreakUI? = null,
    previousBreakUI: BreakUI? = null,
    onClick: () -> Unit,
) {

    val alignment = if (isFirst) Alignment.Start else Alignment.End

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = alignment,
    ) {

        Box {

            BreakWidget(
                modifier = Modifier.alpha(0f),
                breakUI = breakUI ?: previousBreakUI,
                isRight = isFirst,
            )

            AnimatedBreakWidget(
                breakUI = breakUI ?: previousBreakUI,
                isFirst = isFirst,
                isVisible = isActive && breakUI != null,
            )
        }

        PlayerLabel(
            isActive = isActive,
            name = name,
            points = points,
            difference = difference,
            isFirst = isFirst,
            onClick = onClick,
        )
    }
}

@WidgetPreviews
@Composable
private fun PlayerWidgetWithEmptyBreakPreview() {
    RedBlackRepeatTheme {
        PlayerWidgetWithBreakInfo(breakUI = breakMock) {}
    }
}

@WidgetPreviews
@Composable
private fun SecondPlayerWidgetWithEmptyBreakPreview() {
    RedBlackRepeatTheme {
        PlayerWidgetWithBreakInfo(breakUI = breakMock, isFirst = false) {}
    }
}

@WidgetPreviews
@Composable
private fun PlayerWidgetWithBreakPreview() {
    RedBlackRepeatTheme {
        PlayerWidgetWithBreakInfo(breakUI = breakMock, isActive = true, difference = 29) {}
    }
}

@WidgetPreviews
@Composable
private fun SecondPlayerWidgetWithBreakPreview() {
    RedBlackRepeatTheme {
        PlayerWidgetWithBreakInfo(
            breakUI = breakMock,
            isFirst = false,
            isActive = true,
            difference = 29,
        ) {}
    }
}

@WidgetPreviews
@Composable
private fun PlayerWidgetWithBreakInfosPreview() {
    RedBlackRepeatTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            PlayerWidgetWithBreakInfo(breakUI = breakMock) {}
            PlayerWidgetWithBreakInfo(breakUI = breakMock, isActive = true, difference = 29) {}
            PlayerWidgetWithBreakInfo(breakUI = breakMock, isFirst = false, difference = 29) {}
            PlayerWidgetWithBreakInfo(breakUI = breakMock, isFirst = false, isActive = true) {}
        }
    }
}