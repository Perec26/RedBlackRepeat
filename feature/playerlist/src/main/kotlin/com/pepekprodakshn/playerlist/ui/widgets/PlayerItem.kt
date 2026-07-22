package com.pepekprodakshn.playerlist.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.playerlist.ui.firstPlayerUIMock
import com.pepekprodakshn.playerlist.ui.model.PlayerUI

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
            .background(color = MaterialTheme.colorScheme.surfaceContainer),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .size(40.dp)
                .background(
                    color = getBackgroundColor(player),
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = player.name.firstOrNull()?.uppercase() ?: "R",
                style = MaterialTheme.typography.titleMedium,
                color = getAvatarTextColor(player),
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            text = player.name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Checkbox(
            modifier = Modifier.padding(16.dp),
            checked = isSelected,
            enabled = enabled,
            onCheckedChange = null,
        )
    }
}

@Composable
private fun getBackgroundColor(player: PlayerUI) = when (player.hashCode() % 3) {
    0 -> MaterialTheme.colorScheme.primaryContainer
    1 -> MaterialTheme.colorScheme.secondaryContainer
    2 -> MaterialTheme.colorScheme.tertiaryContainer
    else -> MaterialTheme.colorScheme.primary
}

@Composable
private fun getAvatarTextColor(player: PlayerUI) = when (player.hashCode() % 3) {
    0 -> MaterialTheme.colorScheme.onPrimaryContainer
    1 -> MaterialTheme.colorScheme.onSecondaryContainer
    2 -> MaterialTheme.colorScheme.onTertiaryContainer
    else -> MaterialTheme.colorScheme.onPrimary
}

@PreviewLightDark
@Composable
private fun PlayerItemPreview() {
    RedBlackRepeatTheme {
        Column {
            PlayerItem(firstPlayerUIMock)
            PlayerItem(firstPlayerUIMock.copy(id = 2), isSelected = false)
            PlayerItem(firstPlayerUIMock.copy(id = 3), isSelected = false)
            PlayerItem(firstPlayerUIMock.copy(name = "Name"), enabled = false, isSelected = false)
            PlayerItem(firstPlayerUIMock.copy(id = 257), isSelected = false, enabled = false)
        }
    }
}
