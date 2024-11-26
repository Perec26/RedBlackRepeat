package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.isPortrait
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.WidgetPreviews
import com.pepekprodakshn.frame.R
import com.pepekprodakshn.frame.ui.breakMock
import com.pepekprodakshn.frame.ui.model.BallUI
import com.pepekprodakshn.frame.ui.model.BreakUI

@Composable
internal fun AnimatedBreakWidget(
    modifier: Modifier = Modifier,
    breakUI: BreakUI?,
    isFirst: Boolean,
    isVisible: Boolean,
) {

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(initialOffsetX = { if (isFirst) -it else it }),
        exit = slideOutHorizontally(targetOffsetX = { if (isFirst) -it else it }),
    ) {
        Box(
            modifier = modifier
                .width(IntrinsicSize.Max),
        ) {
            BreakWidget(
                breakUI = breakUI,
                isRight = isFirst,
            )
        }
    }
}

@Composable
internal fun BreakWidget(
    modifier: Modifier = Modifier,
    breakUI: BreakUI?,
    isRight: Boolean,
) {

    val shape = when {
        isRight && isPortrait() -> RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp)
        isRight -> RoundedCornerShape(topEnd = 28.dp)
        !isRight && isPortrait() -> RoundedCornerShape(topStart = 28.dp, bottomStart = 28.dp)
        else -> RoundedCornerShape(topStart = 28.dp)
    }

    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = shape,
            ),
        horizontalAlignment = if (isRight) Alignment.Start else Alignment.End,
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
            text = stringResource(R.string.frame_break, breakUI?.sum ?: 0),
            style = MaterialTheme.typography.labelLarge,
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (breakUI != null) {
                if (breakUI.isFreeBall) FreeBallLabel()
                if (breakUI.freeBallScore > 0) FreeBall(count = breakUI.freeBallScore)
                BreakBall(breakUI.reds, BallUI.RED)
                BreakBall(breakUI.yellows, BallUI.YELLOW)
                BreakBall(breakUI.greens, BallUI.GREEN)
                BreakBall(breakUI.browns, BallUI.BROWN)
                BreakBall(breakUI.blues, BallUI.BLUE)
                BreakBall(breakUI.pinks, BallUI.PINK)
                BreakBall(breakUI.blacks, BallUI.BLACK)
            } else {
                Spacer(modifier = Modifier.size(28.dp))
            }
        }
    }
}

@Composable
private fun BreakBall(
    count: Int,
    ballUI: BallUI,
) {
    if (count > 0) {
        Ball(ball = ballUI, size = 28.dp, count = count, showCount = count > 1)
    }
}

@Composable
private fun FreeBallLabel() {
    Box(
        modifier = Modifier
            .height(28.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(28.dp),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.frame_free_ball),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.scrim,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@WidgetPreviews
@Composable
private fun BreakWidgetPreview() {
    RedBlackRepeatTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BreakWidget(
                breakUI = breakMock,
                isRight = true,
            )
            BreakWidget(
                breakUI = breakMock,
                isRight = false,
            )

            BreakWidget(
                breakUI = breakMock.copy(balls = emptyList(), isFreeBall = true),
                isRight = false,
            )
            BreakWidget(
                breakUI = breakMock.copy(balls = emptyList(), freeBallScore = 4),
                isRight = false,
            )
        }
    }
}