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
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallUI
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun BallsWidget(modifier: Modifier = Modifier, onClick: (BallUI) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.frame_balls),
            style = RBRTypography.titleMedium
        )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Ball(ball = BallUI.RED, onClick = onClick)
            Ball(ball = BallUI.YELLOW, onClick = onClick)
            Ball(ball = BallUI.GREEN, onClick = onClick)
            Ball(ball = BallUI.BROWN, onClick = onClick)
        }
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Ball(ball = BallUI.BLUE, onClick = onClick)
            Ball(ball = BallUI.PINK, onClick = onClick)
            Ball(ball = BallUI.BLACK, onClick = onClick)
        }
    }

}

@Preview
@Composable
private fun BallsWidgetPreview() {
    RedBlackRepeatTheme {
        BallsWidgetPreviewContent()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BallsWidgetPreviewDark() {
    RedBlackRepeatTheme {
        BallsWidgetPreviewContent()
    }
}

@Composable
private fun BallsWidgetPreviewContent() {
    BallsWidget {}
}