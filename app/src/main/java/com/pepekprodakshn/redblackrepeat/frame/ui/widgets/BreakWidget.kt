package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.frame.ui.breakMock
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallUI
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BreakUI
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

private const val DIFFERENCE_PADDING = 80

@Composable
fun BreakWidget(
    breakUI: BreakUI,
    isRight: Boolean,
) {

    val shape = if (isRight) {
        RoundedCornerShape(topEnd = 28.dp)
    } else {
        RoundedCornerShape(topStart = 28.dp)
    }

    val startPadding = if (isRight) 0.dp else DIFFERENCE_PADDING.dp
    val endPadding = if (isRight) DIFFERENCE_PADDING.dp else 0.dp

    Column(
        modifier = Modifier
            .padding(start = startPadding, end = endPadding)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = shape
            ),
        horizontalAlignment = if (isRight) Alignment.Start else Alignment.End
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),
            text = stringResource(R.string.frame_break, breakUI.sum),
            style = RBRTypography.titleSmall
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BreakBall(breakUI.reds, BallUI.RED)
            BreakBall(breakUI.yellows, BallUI.YELLOW)
            BreakBall(breakUI.greens, BallUI.GREEN)
            BreakBall(breakUI.browns, BallUI.BROWN)
            BreakBall(breakUI.blues, BallUI.BLUE)
            BreakBall(breakUI.pinks, BallUI.PINK)
            BreakBall(breakUI.blacks, BallUI.BLACK)
        }
    }
}

@Composable
private fun BreakBall(count: Int, ballUI: BallUI) {
    if (count > 0) {
        Ball(ball = ballUI, size = 20.dp, count = count, showCount = count > 1)
    }
}

@Preview(showBackground = true)
@Composable
private fun BreakWidgetPreview() {
    BreakWidgetPreviewContent()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BreakWidgetPreviewDark() {
    BreakWidgetPreviewContent()
}

@Composable
private fun BreakWidgetPreviewContent() {
    RedBlackRepeatTheme {
        Column {
            BreakWidget(breakMock, true)
            SpacerHeight(height = 8.dp)
            BreakWidget(breakMock, false)
        }
    }
}