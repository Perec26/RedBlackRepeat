package com.pepekprodakshn.designsystem.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.pepekprodakshn.designsystem.theme.RBRTypography
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme

@Composable
fun DefaultCounter(
    modifier: Modifier = Modifier,
    value: Int = 0,
    isPlusEnabled: Boolean = true,
    onPlusClick: () -> Unit = {},
    onMinusClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            enabled = value > 0,
            onClick = onMinusClick,
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = "",
            )
        }

        Text(
            text = value.toString(),
            style = RBRTypography.bodyLarge,
        )

        IconButton(
            enabled = isPlusEnabled,
            onClick = onPlusClick,
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "",
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CounterPreview() {
    RedBlackRepeatTheme {
        DefaultCounter()
    }
}