package com.pepekprodakshn.frame.ui.widgets

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.R.drawable
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.SpacerWidth
import com.pepekprodakshn.frame.R

@Composable
internal fun FrameOptionListItem(
    modifier: Modifier = Modifier,
    @StringRes name: Int,
    @DrawableRes icon: Int,
    safeEndContentPadding: Dp = 0.dp,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .padding(end = safeEndContentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Icon(
            painter = painterResource(icon),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            contentDescription = "",
        )

        SpacerWidth(width = 8.dp)

        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@PreviewLightDark
@Composable
private fun FrameOptionListItemPreview() {
    RedBlackRepeatTheme {
        FrameOptionListItem(
            name = R.string.frame_foul,
            icon = drawable.add_circle_24,
        ) {}
    }
}
