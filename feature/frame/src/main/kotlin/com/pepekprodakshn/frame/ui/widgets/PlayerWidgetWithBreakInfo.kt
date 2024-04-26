package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.frame.ui.breakMock
import com.pepekprodakshn.frame.ui.model.BreakUI

@Composable
fun PlayerWidgetWithBreakInfo(
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
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        val alignment = if (isFirst) Alignment.Start else Alignment.End
        val animationOffset = if (isFirst) -1 else 1

        Box(modifier = Modifier.align(alignment)) {
            androidx.compose.animation.AnimatedVisibility(
                enter = slideInHorizontally(initialOffsetX = { animationOffset * it }),
                exit = slideOutHorizontally(targetOffsetX = { animationOffset * it }),
                visible = isActive && breakUI != null,
            ) {
                BreakWidget(
                    breakUI = breakUI ?: previousBreakUI,
                    isRight = isFirst,
                )
            }

            BreakWidget(
                modifier = Modifier.alpha(0f),
                breakUI = breakUI ?: previousBreakUI,
                isRight = isFirst,
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

@PreviewLightDark
@Composable
private fun PlayerWidgetWithBreakInfoPreview() {
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