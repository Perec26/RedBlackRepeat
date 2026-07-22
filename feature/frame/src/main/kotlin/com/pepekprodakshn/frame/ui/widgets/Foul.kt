package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme

@Composable
internal fun Foul(
    modifier: Modifier = Modifier,
    foulValue: Int,
    isSelected: Boolean = false,
    onClick: (Int) -> Unit = {},
) {
    val color = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .aspectRatio(1f)
            .border(
                width = 2.dp,
                color = color,
                shape = CircleShape,
            )
            .clickable(onClick = { onClick(foulValue) }),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier,
            text = foulValue.toString(),
            style = MaterialTheme.typography.headlineSmall,
            color = color,
        )
    }
}

@PreviewLightDark
@Composable
private fun FoulPreview() {
    RedBlackRepeatTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surfaceContainerHigh)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            (4..7).forEach {
                Foul(
                    modifier = Modifier.weight(1f),
                    foulValue = it,
                    isSelected = it % 2 > 0,
                )
            }
        }
    }
}
