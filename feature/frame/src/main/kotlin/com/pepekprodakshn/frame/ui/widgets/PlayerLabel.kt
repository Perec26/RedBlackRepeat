package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.SpacerHeight

@Composable
internal fun PlayerLabel(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    name: String = "Ronnie O'Sullivan",
    points: Int = 102,
    difference: Int = 1,
    isFirst: Boolean = true,
    safeContentPadding: PaddingValues = PaddingValues(),
    onClick: () -> Unit,
) {
    val backgroundColor = if (isActive) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceContainer
    }

    val backgroundShape = RoundedCornerShape(
        topStart = if (isFirst) 0.dp else 50.dp,
        bottomStart = if (isFirst) 0.dp else 50.dp,
        topEnd = if (isFirst) 50.dp else 0.dp,
        bottomEnd = if (isFirst) 50.dp else 0.dp,
    )

    val paddingValues = PaddingValues(
        start = if (isFirst) 0.dp else 80.dp,
        end = if (isFirst) 80.dp else 0.dp,
    )
    val align = if (isFirst) Alignment.CenterEnd else Alignment.CenterStart

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        val direction = if (isFirst) -1 else 1
        AnimatedVisibility(
            modifier = Modifier.align(align),
            visible = difference > 0,
            enter = slideInHorizontally(initialOffsetX = { direction * it / 2 }),
            exit = slideOutHorizontally(targetOffsetX = { direction * it / 2 })
        ) {
            Difference(align, difference)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .zIndex(1f)
                .clip(backgroundShape)
                .clickable(onClick = onClick)
                .background(color = backgroundColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isFirst) {
                LeftRow(
                    name = name,
                    points = points,
                    textColor = contentColorFor(backgroundColor),
                    safeContentPadding = safeContentPadding.calculateLeftPadding(
                        LayoutDirection.Ltr,
                    ),
                )
            } else {
                RightRow(
                    name = name,
                    points = points,
                    textColor = contentColorFor(backgroundColor),
                    safeContentPadding = safeContentPadding.calculateRightPadding(
                        LayoutDirection.Ltr,
                    ),
                )
            }
        }
    }
}

@Composable
fun BoxScope.Difference(align: Alignment, difference: Int) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .width(150.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(50.dp),
            )
            .align(align),
    ) {
        PlayerText(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(align),
            text = "+$difference",
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun RowScope.LeftRow(
    name: String,
    points: Int,
    textColor: Color,
    safeContentPadding: Dp = 0.dp
) {
    PlayerText(
        modifier = Modifier
            .padding(start = safeContentPadding)
            .weight(1f),
        text = name,
        color = textColor,
    )
    PlayerText(
        modifier = Modifier.padding(end = 8.dp),
        text = points.toString(),
        color = textColor,
    )
}

@Composable
private fun RowScope.RightRow(
    name: String,
    points: Int,
    textColor: Color,
    safeContentPadding: Dp = 0.dp
) {
    PlayerText(
        modifier = Modifier.padding(start = 8.dp),
        text = points.toString(),
        color = textColor,
    )
    PlayerText(
        modifier = Modifier
            .padding(end = safeContentPadding)
            .weight(1f),
        text = name,
        color = textColor,
    )
}

@Composable
private fun PlayerText(modifier: Modifier = Modifier, text: String, color: Color) {
    Text(
        modifier = modifier.padding(16.dp),
        text = text,
        color = color,
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@PreviewLightDark
@Preview(device = "spec:width=600px,height=2340px,dpi=440")
@Composable
private fun PlayerLabelPreview() {
    RedBlackRepeatTheme {
        Column {
            PlayerLabel {}
            SpacerHeight(height = 8.dp)
            PlayerLabel(isActive = true, difference = 29) {}
            SpacerHeight(height = 8.dp)
            PlayerLabel(isFirst = false, difference = 290) {}
            SpacerHeight(height = 8.dp)
            PlayerLabel(isFirst = false, isActive = true) {}
        }
    }
}
