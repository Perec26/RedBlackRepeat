package com.pepekprodakshn.redblackrepeat.frame.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.CheckBoxWithText
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.DefaultCounter
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.DefaultDialog
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.DefaultTextButton
import com.pepekprodakshn.redblackrepeat.frame.ui.model.FoulUI
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.Foul
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun FoulDialog(
    foulUI: FoulUI,
    onEvent: (FrameEvent) -> Unit,
) {
    DefaultDialog(onDismissRequest = { onEvent(FrameEvent.OnFoulBottomSheetClosed) }) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.frame_fouls),
                style = RBRTypography.titleMedium,
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                (foulUI.lowestBallValue..7).forEach {
                    Foul(
                        modifier = Modifier.padding(8.dp),
                        foulValue = it,
                        isSelected = foulUI.points == it,
                        onClick = { points -> onEvent(FrameEvent.OnFoulPointClick(points)) },
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {

                CheckBoxWithText(
                    isChecked = foulUI.isMiss,
                    text = stringResource(R.string.frame_miss),
                    onClick = { onEvent(FrameEvent.OnFoulIsMissClick) },
                )

                CheckBoxWithText(
                    isChecked = foulUI.isFreeBall,
                    text = stringResource(R.string.frame_free_ball),
                    onClick = { onEvent(FrameEvent.OnFoulIsFreeBallClick) },
                )
            }

            if (foulUI.showRemoveReds) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Remove reds",
                        style = RBRTypography.bodyMedium,
                    )
                    DefaultCounter(
                        value = foulUI.removeReds,
                        isPlusEnabled = foulUI.canAddReds,
                        onPlusClick = { onEvent(FrameEvent.OnFoulAddRedsClick) },
                        onMinusClick = { onEvent(FrameEvent.OnFoulRemoveRedsClick) },
                    )
                }
            }

            DefaultTextButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.End),
                text = stringResource(R.string.common_confirm),
                isEnable = foulUI.points > 0,
                onClick = { onEvent(FrameEvent.OnFoulConfirmClick) },
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun FoulBottomSheetPreview() {
    RedBlackRepeatTheme {
        FoulDialog(FoulUI(points = 4, lowestBallValue = 6)) {}
    }
}