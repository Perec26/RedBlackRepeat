package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

private const val DIFFERENCE_PADDING = 50

@Composable
fun PlayerLabel(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    name: String = "Ronnie O'Sullivan",
    points: Int = 102,
    difference: Int = 0,
    isFirst: Boolean = true,
    onClick: () -> Unit,
) {

    val differenceOffset by animateDpAsState(
        targetValue = if (difference > 0) 0.dp else 51.dp,
        label = "differenceOffset",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    val (backgroundColor, textColor) = if (isActive) {
        MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceContainerLow to MaterialTheme.colorScheme.onSurface
    }

    val backgroundShape = RoundedCornerShape(
        topStart = if (isFirst) 0.dp else 50.dp,
        bottomStart = if (isFirst) 0.dp else 50.dp,
        topEnd = if (isFirst) 50.dp else 0.dp,
        bottomEnd = if (isFirst) 50.dp else 0.dp,
    )


    Box(
        modifier = modifier.fillMaxWidth(),
    ) {

        val startPadding = if (isFirst) 0.dp else DIFFERENCE_PADDING.dp
        val endPadding = if (isFirst) DIFFERENCE_PADDING.dp else 0.dp

        val align = if (isFirst) Alignment.CenterEnd else Alignment.CenterStart


        Box(
            modifier = Modifier
                .align(align)
                .offset(
                    x = if (isFirst) -differenceOffset else differenceOffset
                )
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = backgroundShape
                )
        ) {
            Row {
                PlayerText(
                    modifier = Modifier.padding(start = endPadding * 2, end = startPadding * 2),
                    text = "+$difference",
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }


        Surface(
            color = backgroundColor
        ) {

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
                .padding(start = startPadding, end = endPadding)
                .clip(backgroundShape)
                .clickable(onClick = onClick)
                .background(color = backgroundColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isFirst) {
                RightRow(name = name, points = points, textColor = textColor)
            } else {
                LeftRow(name = name, points = points, textColor = textColor)
            }
        }


    }
}


@Composable
private fun RightRow(
    name: String,
    points: Int,
    textColor: Color,
) {
    PlayerText(
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
private fun LeftRow(
    name: String,
    points: Int,
    textColor: Color,
) {
    PlayerText(
        modifier = Modifier.padding(start = 8.dp),
        text = points.toString(),
        color = textColor,
    )
    PlayerText(
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

@Preview(showBackground = true)
@Composable
private fun PlayerLabelPreview() {
    RedBlackRepeatTheme {
        PlayerLabelPreviewContent()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PlayerLabelPreviewDark() {
    RedBlackRepeatTheme {
        PlayerLabelPreviewContent()
    }
}

@Composable
private fun PlayerLabelPreviewContent() {
    Column {
        PlayerLabel {}
        SpacerHeight(height = 8.dp)
        PlayerLabel(isActive = true, difference = 29) {}
        SpacerHeight(height = 8.dp)
        PlayerLabel(isFirst = false, difference = 29) {}
        SpacerHeight(height = 8.dp)
        PlayerLabel(isFirst = false, isActive = true) {}
    }
}