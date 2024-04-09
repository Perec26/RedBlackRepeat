package com.pepekprodakshn.redblackrepeat.choosePlayer.ui.widgets

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
import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI
import com.pepekprodakshn.redblackrepeat.frame.ui.firstPlayerUIMock
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun PlayerItem(
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
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = player.name.firstOrNull()?.uppercase() ?: "R",
                style = RBRTypography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            text = player.name,
            style = RBRTypography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Checkbox(
            modifier = Modifier.padding(8.dp),
            checked = isSelected,
            enabled = enabled,
            onCheckedChange = null,
        )
    }
}

@PreviewLightDark
@Composable
private fun PlayerItemPreview() {
    RedBlackRepeatTheme {
        Column {
            PlayerItem(firstPlayerUIMock)
            PlayerItem(firstPlayerUIMock, isSelected = false)
            PlayerItem(firstPlayerUIMock, isSelected = false, enabled = false)
        }
    }
}