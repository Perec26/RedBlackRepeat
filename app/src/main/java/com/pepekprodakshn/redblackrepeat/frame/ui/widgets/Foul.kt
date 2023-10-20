package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerWidth
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun Foul(
    foulValue: Int,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: (Int) -> Unit = {},
) {

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = color,
                shape = CircleShape
            )
            .clickable(onClick = { onClick(foulValue) }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = foulValue.toString(),
            style = MaterialTheme.typography.headlineSmall,
            color = color
        )
    }
}

@Preview
@Composable
private fun FoulPreview() {
    RedBlackRepeatTheme {
        FoulPreviewContent()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FoulPreviewDark() {
    RedBlackRepeatTheme {
        FoulPreviewContent()
    }
}


@Composable
private fun FoulPreviewContent() {
    Column {
        SpacerHeight(8.dp)

        Row {
            (4..7).forEach {
                SpacerWidth(8.dp)
                Foul(it)
            }
            SpacerWidth(8.dp)
        }

        SpacerHeight(8.dp)
    }
}