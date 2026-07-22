package com.pepekprodakshn.frame.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.frame.R
import com.pepekprodakshn.frame.ui.model.BallUI
import kotlin.math.sqrt

@Composable
internal fun Ball(
    ball: BallUI,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    count: Int = 0,
    showCount: Boolean = false,
    isEnabled: Boolean = true,
    onClick: (BallUI) -> Unit = {},
) {
    Ball(
        modifier = modifier,
        ballIcon = ball.icon,
        ballTextColor = ball.textColor,
        size = size,
        count = count,
        showCount = showCount,
        isEnabled = isEnabled,
        onClick = { onClick(ball) },
    )
}

@Composable
internal fun FreeBall(modifier: Modifier = Modifier, count: Int = 0) {
    Ball(
        modifier = modifier,
        ballIcon = R.drawable.ic_ball_full_white,
        ballTextColor = MaterialTheme.colorScheme.scrim,
        size = 28.dp,
        count = count,
        showCount = true,
        isEnabled = true,
    )
}

@Composable
private fun Ball(
    modifier: Modifier = Modifier,
    @DrawableRes ballIcon: Int,
    ballTextColor: Color,
    size: Dp = 64.dp,
    count: Int = 0,
    showCount: Boolean = false,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val alpha = if (isEnabled) 1f else 0.5f
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .alpha(alpha)
                .clickable(onClick = onClick, enabled = isEnabled),
            painter = painterResource(ballIcon),
            tint = Color.Unspecified,
            contentDescription = "",
        )

        val textSize = MaterialTheme.typography.titleLarge.fontSize / sqrt((64.0 / size.value))
        if (showCount) {
            Text(
                text = count.toString(),
                color = ballTextColor.copy(alpha),
                style = MaterialTheme.typography.titleLarge.copy(fontSize = textSize),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BallPreview() {
    RedBlackRepeatTheme {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BallRowPreview(true)
            BallRowPreview(false)
            BallRowPreview(true, 24.dp)
            BallRowPreview(false, 24.dp)
        }
    }
}

@Composable
private fun BallRowPreview(isEnabled: Boolean, size: Dp = 64.dp) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BallUI.entries.forEach {
            Ball(
                ball = it,
                count = it.value * 2,
                size = size,
                showCount = true,
                isEnabled = isEnabled,
            )
        }
    }
}
