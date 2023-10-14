package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerWidth
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallVO
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun Ball(
    ball: BallVO,
    modifier: Modifier = Modifier,
    size: Dp = 36.dp,
    onClick: (BallVO) -> Unit = {}
) {
    Spacer(
        modifier = modifier
            .size(size)
            .background(ball.color, CircleShape)
            .clickable(onClick = { onClick(ball) })
    )
}

@Preview
@Composable
fun BallPreview() {
    RedBlackRepeatTheme {
        Column {
            SpacerHeight(8)

            Row {
                BallVO.values().forEach {
                    SpacerWidth(8)
                    Ball(it)
                }
                SpacerWidth(8)
            }

            SpacerHeight(8)
        }
    }
}