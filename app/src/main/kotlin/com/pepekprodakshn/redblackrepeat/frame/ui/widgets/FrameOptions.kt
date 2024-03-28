package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.ColumnWithMoreElement
import com.pepekprodakshn.redblackrepeat.frame.ui.FrameEvent
import com.pepekprodakshn.redblackrepeat.frame.ui.model.FrameOptionUI
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme


@Composable
fun FrameOptions(
    modifier: Modifier = Modifier,
    onEvent: (FrameEvent) -> Unit,
) {

    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
            )
            .width(IntrinsicSize.Max)
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.frame_options),
            style = RBRTypography.titleMedium
        )


        ColumnWithMoreElement(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)),

            onElementsCounted = { onEvent(FrameEvent.OnOptionsElementsCounted(it)) },

            moreElement = {
                FrameOption(
                    modifier = Modifier.padding(bottom = 8.dp),
                    name = R.string.frame_more,
                    iconImageVector = Icons.Filled.MoreHoriz,
                    onClick = { onEvent(FrameEvent.OnMoreClick) }
                )
            },
        ) {

            FrameOptionUI.entries.forEach {
                FrameOption(
                    name = it.text,
                    iconImageVector = it.icon,
                    onClick = { onEvent(it.event) }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun FrameOptionsPreview() {
    RedBlackRepeatTheme {
        FrameOptions(modifier = Modifier) {}
    }
}