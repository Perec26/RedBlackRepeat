package com.pepekprodakshn.playerlist.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.WidgetPreviews
import com.pepekprodakshn.playerlist.ui.firstPlayerUIMock
import com.pepekprodakshn.playerlist.ui.model.PlayerUI
import kotlin.math.abs

@Composable
internal fun PlayerItem(
    player: PlayerUI,
    enabled: Boolean = true,
    isSelected: Boolean = true,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = enabled || isSelected,
                onClick = onClick,
            )
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val color = getBackgroundColor(player)
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = color,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = player.name.firstOrNull()?.uppercase() ?: "R",
                style = MaterialTheme.typography.titleMedium,
                color = contentColorFor(color),
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            text = player.name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Checkbox(
            checked = isSelected,
            enabled = enabled,
            onCheckedChange = null,
        )
    }
}

@Composable
private fun getBackgroundColor(player: PlayerUI) = when (abs(player.hashCode()) % 3) {
    0 -> MaterialTheme.colorScheme.primaryContainer
    1 -> MaterialTheme.colorScheme.secondaryContainer
    2 -> MaterialTheme.colorScheme.tertiaryContainer
    else -> MaterialTheme.colorScheme.primary
}

@WidgetPreviews
@Composable
private fun PlayerItemPreview() {
    RedBlackRepeatTheme {
        Column {
            PlayerItem(firstPlayerUIMock.copy(name = "Very long name that doesn't fit to lines"))
            PlayerItem(firstPlayerUIMock)
            PlayerItem(firstPlayerUIMock.copy(id = 2), isSelected = false)
            PlayerItem(firstPlayerUIMock.copy(id = 3), isSelected = false)
            PlayerItem(
                firstPlayerUIMock.copy(name = "Name"),
                enabled = false,
                isSelected = false
            )
            PlayerItem(firstPlayerUIMock.copy(id = 257), isSelected = false, enabled = false)
        }
    }
}
