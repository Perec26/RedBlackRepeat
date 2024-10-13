package com.pepekprodakshn.frame.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RBRTypography
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.frame.R
import com.pepekprodakshn.frame.ui.model.BallUI
import com.pepekprodakshn.frame.ui.model.BallsStateUI

@Composable
internal fun BallsWidget(
    modifier: Modifier = Modifier,
    ballsState: BallsStateUI,
    onClick: (BallUI) -> Unit,
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.frame_balls),
            style = RBRTypography.titleMedium,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Ball(
                ball = BallUI.RED,
                onClick = onClick,
                count = ballsState.redsCount,
                showCount = ballsState.showRedsCount,
                isEnabled = ballsState.redsEnabled,
            )
            Ball(ball = BallUI.YELLOW, onClick = onClick, isEnabled = ballsState.yellowEnabled)
            Ball(ball = BallUI.GREEN, onClick = onClick, isEnabled = ballsState.greenEnabled)
            Ball(ball = BallUI.BROWN, onClick = onClick, isEnabled = ballsState.brownEnabled)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Ball(ball = BallUI.BLUE, onClick = onClick, isEnabled = ballsState.blueEnabled)
            Ball(ball = BallUI.PINK, onClick = onClick, isEnabled = ballsState.pinkEnabled)
            Ball(ball = BallUI.BLACK, onClick = onClick, isEnabled = ballsState.blackEnabled)
        }
    }
}

@Preview
@Composable
private fun BallsWidgetPreview() {
    RedBlackRepeatTheme {
        BallsWidget(ballsState = BallsStateUI()) {}
    }
}