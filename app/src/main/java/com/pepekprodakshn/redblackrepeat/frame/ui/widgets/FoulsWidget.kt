package com.pepekprodakshn.redblackrepeat.frame.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun FoulsWidget(
    modifier: Modifier = Modifier,
    onFoulClick: (Int) -> Unit,
    onRemoveClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                shape = RoundedCornerShape(topStart = 28.dp, bottomStart = 28.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.frame_fouls),
            style = RBRTypography.titleMedium
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            (4..7).forEach {
                Foul(foulValue = it, onClick = onFoulClick)
            }
        }
        Text(
            text = stringResource(R.string.frame_remove),
            style = RBRTypography.titleMedium
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            (-4 downTo -7).forEach {
                Foul(
                    foulValue = it,
                    color = MaterialTheme.colorScheme.error,
                    onClick = onRemoveClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FoulsWidgetPreview() {
    FoulsWidgetPreviewContent()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FoulsWidgetPreviewDark() {
    FoulsWidgetPreviewContent()
}

@Composable
private fun FoulsWidgetPreviewContent() {
    RedBlackRepeatTheme {
        FoulsWidget(
            onFoulClick = {},
            onRemoveClick = {}
        )
    }
}