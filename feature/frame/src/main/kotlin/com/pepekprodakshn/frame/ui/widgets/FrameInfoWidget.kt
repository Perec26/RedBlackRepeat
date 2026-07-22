package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.isPortrait
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.DefaultHorizontalDivider
import com.pepekprodakshn.designsystem.widgets.SpacerHeight
import com.pepekprodakshn.designsystem.widgets.WidgetPreviews
import com.pepekprodakshn.frame.R
import com.pepekprodakshn.frame.ui.model.FrameInfoUI

@Composable
internal fun FrameInfoWidget(
    modifier: Modifier = Modifier,
    safeContentPadding: PaddingValues = PaddingValues(),
    info: FrameInfoUI,
) {
    val topPadding = if (isPortrait()) 0.dp else safeContentPadding.calculateTopPadding()

    val shape = if (isPortrait()) {
        RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
    } else {
        RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    }

    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = shape,
            )
            .padding(top = topPadding)
            .width(IntrinsicSize.Max),
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.frame_info),
            style = MaterialTheme.typography.titleMedium,
        )

        FrameInfoItem(
            name = stringResource(R.string.frame_points_on_table),
            value = "${info.pointsOnTable}",
        )

        DefaultHorizontalDivider()

        FrameInfoItem(
            name = stringResource(R.string.frame_snookers_required),
            value = "${info.snookersRequired}",
        )
        SpacerHeight(16.dp)
    }
}

@WidgetPreviews
@Composable
private fun FrameInfoWidgetPreview() {
    RedBlackRepeatTheme {
        FrameInfoWidget(
            info = FrameInfoUI(
                pointsOnTable = 10,
                snookersRequired = 10,
            ),
        )
    }
}
