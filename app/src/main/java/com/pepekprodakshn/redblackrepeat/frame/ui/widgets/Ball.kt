package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerWidth
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
    onClick: (BallUI) -> Unit = {},
) {

    Box(
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
                .clickable(onClick = { onClick(ball) }),
            painter = painterResource(id = R.drawable.ic_ball),
            tint = ball.color,
            contentDescription = ""
        )

        val textSize = 24.sp / (64.dp / size)
        if (showCount) {
            Text(
                text = count.toString(),
                color = ball.textColor,
                style = RBRTypography.titleMedium.copy(fontSize = textSize)
            )
        }
    }

}

@Preview
@Composable
private fun BallPreview() {
    RedBlackRepeatTheme {
        BallPreviewContent()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BallPreviewDark() {
    RedBlackRepeatTheme {
        BallPreviewContent()
    }
}


@Composable
private fun BallPreviewContent() {
    Column {
        SpacerHeight(8.dp)

        Row {
            BallUI.values().forEach {
                SpacerWidth(8.dp)
                Ball(it, count = it.value * 2, size = 24.dp)
            }
            SpacerWidth(8.dp)
        }

        SpacerHeight(8.dp)
    }
}