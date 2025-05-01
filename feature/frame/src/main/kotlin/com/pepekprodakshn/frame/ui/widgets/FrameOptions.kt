package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.ColumnWithMoreElement
import com.pepekprodakshn.designsystem.widgets.WidgetPreviews
import com.pepekprodakshn.frame.R
import com.pepekprodakshn.frame.ui.FrameEvent
import com.pepekprodakshn.frame.ui.model.FrameOptionUI

@Composable
internal fun FrameOptions(
    modifier: Modifier = Modifier,
    safeContentPadding: PaddingValues = PaddingValues(),
    onEvent: (FrameEvent) -> Unit,
) {

    val endPadding = safeContentPadding.calculateEndPadding(LayoutDirection.Ltr)
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
            )
            .width(IntrinsicSize.Max),
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.frame_options),
            style = MaterialTheme.typography.titleMedium,
        )

        ColumnWithMoreElement(
            modifier = Modifier.width(IntrinsicSize.Max),
            onElementsCounted = { onEvent(FrameEvent.OnOptionsElementsCounted(it)) },
            moreElement = {
                FrameOptionListItem(
                    modifier = Modifier.padding(bottom = 8.dp),
                    name = R.string.frame_more,
                    iconImageVector = Icons.Filled.MoreHoriz,
                    safeEndContentPadding = endPadding,
                    onClick = { onEvent(FrameEvent.OnMoreClick) },
                )
            },
        ) {

            FrameOptionUI.entries.forEach {
                FrameOptionListItem(
                    name = it.text,
                    iconImageVector = it.icon,
                    safeEndContentPadding = endPadding,
                    onClick = { onEvent(it.event) },
                )
            }
        }
    }
}

@WidgetPreviews
@Composable
private fun FrameOptionsPreview() {
    RedBlackRepeatTheme {
        FrameOptions(modifier = Modifier) {}
    }
}