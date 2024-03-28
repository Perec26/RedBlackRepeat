package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallUI
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme


@Composable
fun Ball(
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
        ballColor = ball.color,
        ballTextColor = ball.textColor,
        size = size,
        count = count,
        showCount = showCount,
        isEnabled = isEnabled,
        onClick = { onClick(ball) }
    )
}

@Composable
fun FreeBall(
    modifier: Modifier = Modifier,
    count: Int = 0,
) {
    Ball(
        modifier = modifier,
        ballColor = Color.White,
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
    ballColor: Color,
    ballTextColor: Color,
    size: Dp = 64.dp,
    count: Int = 0,
    showCount: Boolean = false,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val alpha = if (isEnabled) 1f else 0.6f

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            modifier = Modifier
                .size(size - 1.dp)
                .background(
                    color = Color.White,
                    shape = CircleShape,
                )
        )
        Icon(
            modifier = modifier
                .size(size)
                .clip(CircleShape)
                .clickable(onClick = onClick, enabled = isEnabled),
            painter = painterResource(id = R.drawable.ic_ball),
            tint = ballColor.copy(alpha = alpha),
            contentDescription = ""
        )

        val textSize = 24.sp / (64.dp / size)
        if (showCount) {
            Text(
                text = count.toString(),
                color = ballTextColor,
                style = RBRTypography.titleMedium.copy(fontSize = textSize)
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BallRowPreview(true)
            BallRowPreview(false)
        }
    }
}

@Composable
private fun BallRowPreview(isEnabled: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BallUI.entries.forEach {
            Ball(
                ball = it,
                count = it.value * 2,
                size = 24.dp,
                showCount = true,
                isEnabled = isEnabled,
            )
        }
    }
}

