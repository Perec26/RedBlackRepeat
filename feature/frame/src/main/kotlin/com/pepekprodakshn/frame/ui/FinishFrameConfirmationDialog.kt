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
import com.pepekprodakshn.designsystem.widgets.DefaultDialog
import com.pepekprodakshn.designsystem.widgets.DefaultTextButton
import com.pepekprodakshn.frame.R

@Composable
internal fun FinishFrameConfirmationDialog(
    onEvent: (FrameEvent) -> Unit,
) {
    DefaultDialog(onDismissRequest = { onEvent(FrameEvent.OnFinishFrameConfirmationClosed) }) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.frame_finish_frame_dialog_title),
                style = RBRTypography.titleMedium,
            )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.frame_finish_frame_dialog_description),
                style = RBRTypography.bodyMedium,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End,
            ) {

                DefaultTextButton(
                    text = stringResource(id = R.string.frame_finish_frame_dialog_no),
                    onClick = { onEvent(FrameEvent.OnFinishFrameConfirmationClosed) },
                )

                DefaultTextButton(
                    text = stringResource(id = R.string.frame_finish_frame_dialog_yes),
                    onClick = { onEvent(FrameEvent.OnFinishFrameConfirm) },
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun FinishFrameConfirmationDialogPreview() {
    RedBlackRepeatTheme {
        FinishFrameConfirmationDialog {}
    }
}