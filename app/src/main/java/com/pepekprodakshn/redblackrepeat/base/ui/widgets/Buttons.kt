package com.pepekprodakshn.redblackrepeat.base.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun DefaultFilledButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnable: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        enabled = isEnable,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = RBRTypography.labelLarge
        )
    }
}

@Composable
fun DefaultTextButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnable: Boolean = true,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        enabled = isEnable,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = RBRTypography.labelLarge
        )
    }
}

@PreviewLightDark
@Composable
private fun ButtonsPreview() {
    RedBlackRepeatTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DefaultFilledButton(text = "Text") {}
            DefaultTextButton(text = "Text") {}
        }
    }
}