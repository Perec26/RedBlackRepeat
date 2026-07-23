package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.isPortrait
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.WidgetPreviews
import com.pepekprodakshn.frame.R
import com.pepekprodakshn.frame.ui.model.BallUI
import com.pepekprodakshn.frame.ui.model.BallsStateUI

@Composable
internal fun BallsWidget(
    modifier: Modifier = Modifier,
    safeContentPadding: PaddingValues = PaddingValues(),
    ballsState: BallsStateUI,
    onClick: (BallUI) -> Unit,
) {
    val shape = if (isPortrait()) {
        RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
    } else {
        RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp)
    }

    val startPadding = if (isPortrait()) {
        0.dp
    } else {
        safeContentPadding.calculateLeftPadding(LayoutDirection.Ltr)
    }
    val topPadding = if (isPortrait()) safeContentPadding.calculateTopPadding() else 0.dp

    val horizontalPadding = if (isPortrait()) 16.dp else 0.dp
    val verticalPadding = if (isPortrait()) 0.dp else 16.dp

    Box(
        modifier = modifier
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = shape,
            ),
    ) {
        Column(
            modifier = Modifier
                .padding(start = startPadding, top = topPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.frame_balls),
                style = MaterialTheme.typography.titleMedium,
            )

            BoxWithConstraints {
                val spacing = 8.dp
                val ballSizeByWidth = (maxWidth - spacing * 3) / 4
                val ballSizeByHeight = (maxHeight - spacing) / 2
                val ballSize = minOf(ballSizeByHeight, ballSizeByWidth, 100.dp)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 8.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                    ) {
                        Ball(
                            size = ballSize,
                            ball = BallUI.RED,
                            onClick = onClick,
                            count = ballsState.redsCount,
                            showCount = ballsState.showRedsCount,
                            isEnabled = ballsState.redsEnabled,
                        )
                        Ball(
                            size = ballSize,
                            ball = BallUI.YELLOW,
                            onClick = onClick,
                            isEnabled = ballsState.yellowEnabled
                        )
                        Ball(
                            size = ballSize,
                            ball = BallUI.GREEN,
                            onClick = onClick,
                            isEnabled = ballsState.greenEnabled
                        )
                        Ball(
                            size = ballSize,
                            ball = BallUI.BROWN,
                            onClick = onClick,
                            isEnabled = ballsState.brownEnabled
                        )
                    }

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(
                            space = 8.dp,
                            alignment = Alignment.CenterHorizontally
                        )
                    ) {
                        Ball(
                            size = ballSize,
                            ball = BallUI.BLUE,
                            onClick = onClick,
                            isEnabled = ballsState.blueEnabled
                        )
                        Ball(
                            size = ballSize,
                            ball = BallUI.PINK,
                            onClick = onClick,
                            isEnabled = ballsState.pinkEnabled
                        )
                        Ball(
                            size = ballSize,
                            ball = BallUI.BLACK,
                            onClick = onClick,
                            isEnabled = ballsState.blackEnabled
                        )
                    }
                }
            }
        }
    }
}

@WidgetPreviews
@Composable
private fun BallsWidgetPreview() {
    RedBlackRepeatTheme {
        BallsWidget(ballsState = BallsStateUI()) {}
    }
}
