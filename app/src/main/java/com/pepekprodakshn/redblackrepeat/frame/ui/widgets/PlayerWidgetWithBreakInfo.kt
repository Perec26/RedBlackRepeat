package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.frame.ui.breakMock
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BreakUI
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun PlayerWidgetWithBreakInfo(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    name: String = "Ronnie O'Sullivan",
    points: Int = 102,
    difference: Int = 0,
    isFirst: Boolean = true,
    breakUI: BreakUI? = null,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        breakUI?.let {
            if (isActive) BreakWidget(breakUI = it, isFirst)
        }
        PlayerLabel(
            isActive = isActive,
            name = name,
            points = points,
            difference = difference,
            isFirst = isFirst,
            onClick = onClick
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PlayerWidgetWithBreakInfoPreview() {
    PlayerWidgetWithBreakInfoPreviewContent()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PlayerWidgetWithBreakInfoPreviewDark() {
    PlayerWidgetWithBreakInfoPreviewContent()
}

@Composable
private fun PlayerWidgetWithBreakInfoPreviewContent() {
    RedBlackRepeatTheme {
        Column {
            PlayerWidgetWithBreakInfo(breakUI = breakMock) {}
            SpacerHeight(height = 8.dp)
            PlayerWidgetWithBreakInfo(breakUI = breakMock, isActive = true, difference = 29) {}
            SpacerHeight(height = 8.dp)
            PlayerWidgetWithBreakInfo(breakUI = breakMock, isFirst = false, difference = 29) {}
            SpacerHeight(height = 8.dp)
            PlayerWidgetWithBreakInfo(breakUI = breakMock, isFirst = false, isActive = true) {}
        }
    }
}