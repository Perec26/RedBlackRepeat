package com.pepekprodakshn.frame.ui.widgets

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.R.drawable
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.frame.R

@Composable
internal fun FrameOptionGridItem(
    modifier: Modifier = Modifier,
    @StringRes name: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                shape = RoundedCornerShape(12.dp),
            )
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = modifier.size(36.dp),
                painter = painterResource(icon),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = "",
            )

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = name),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun FrameOptionGridItemPreview() {
    RedBlackRepeatTheme {
        FrameOptionGridItem(
            name = R.string.frame_foul,
            icon = drawable.add_circle_24,
        ) {}
    }
}
