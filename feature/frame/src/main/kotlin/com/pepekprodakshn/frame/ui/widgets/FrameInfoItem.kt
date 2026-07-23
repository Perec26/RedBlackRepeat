package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.SpacerWidth
import com.pepekprodakshn.designsystem.widgets.WidgetPreviews

@Composable
internal fun FrameInfoItem(modifier: Modifier = Modifier, name: String, value: String) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        SpacerWidth(8.dp)

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
        )
    }
}

@WidgetPreviews
@Composable
private fun FrameInfoItemPreview() {
    RedBlackRepeatTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            FrameInfoItem(name = "Very long text that not fit to space", value = "value1")
            FrameInfoItem(name = "name2", value = "value2")
            FrameInfoItem(name = "name3", value = "value3")
            FrameInfoItem(name = "name4", value = "value4")
        }
    }
}
