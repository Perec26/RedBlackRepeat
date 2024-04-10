package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerWidth
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun FrameOptionListItem(
    modifier: Modifier = Modifier,
    @StringRes name: Int,
    iconImageVector: ImageVector,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {

        Icon(
            imageVector = iconImageVector,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = "",
        )

        SpacerWidth(width = 8.dp)

        Text(
            text = stringResource(id = name),
            style = RBRTypography.bodyMedium,
        )
    }
}

@PreviewLightDark
@Composable
private fun FrameOptionListItemPreview() {
    RedBlackRepeatTheme {
        FrameOptionListItem(
            name = R.string.frame_foul,
            iconImageVector = Icons.Filled.AddCircle,
        ) {}
    }
}