package com.pepekprodakshn.frame.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RBRTypography
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.DefaultCounter
import com.pepekprodakshn.designsystem.widgets.DefaultDialog
import com.pepekprodakshn.designsystem.widgets.DefaultTextButton
import com.pepekprodakshn.frame.R
import com.pepekprodakshn.frame.ui.model.AddRemoveDialogState

@Composable
internal fun AddRemoveRedDialog(
    state: AddRemoveDialogState,
    onEvent: (FrameEvent) -> Unit,
) {

    DefaultDialog(onDismissRequest = { onEvent(FrameEvent.OnAddRemoveRedsDialogClose) }) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            val titleRes = if (state.isAdd) R.string.frame_add_reds else R.string.frame_remove_reds

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = titleRes),
                style = RBRTypography.titleMedium,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = stringResource(id = R.string.frame_reds),
                    style = RBRTypography.bodyMedium,
                )

                DefaultCounter(
                    value = state.redsCount,
                    isPlusEnabled = state.isPlusEnabled,
                    onPlusClick = { onEvent(FrameEvent.OnAddRemoveRedsPlusClick) },
                    onMinusClick = { onEvent(FrameEvent.OnAddRemoveRedsMinusClick) },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End,
            ) {

                val (event, buttonName) = if (state.isAdd) {
                    FrameEvent.OnAddRedsDialogClick to R.string.frame_add
                } else {
                    FrameEvent.OnRemoveRedsDialogClick to R.string.frame_remove
                }

                DefaultTextButton(
                    text = stringResource(id = R.string.frame_cancel),
                    onClick = { onEvent(event) },
                )

                DefaultTextButton(
                    text = stringResource(id = buttonName),
                    isEnable = state.redsCount > 0,
                    onClick = { onEvent(event) },
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun AddRemoveRedDialogPreview() {
    RedBlackRepeatTheme {
        AddRemoveRedDialog(state = AddRemoveDialogState(isAdd = true)) {}
    }
}