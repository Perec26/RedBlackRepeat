package com.pepekprodakshn.redblackrepeat.base.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun DefaultDivider(
    modifier: Modifier = Modifier,
    startPadding: Dp = 16.dp,
    endPadding: Dp = 16.dp,
) {
    HorizontalDivider(modifier = modifier.padding(start = startPadding, end = endPadding))
}

@PreviewLightDark
@Composable
private fun DefaultDividerPreview() {
    RedBlackRepeatTheme {
        DefaultDivider()
    }
}