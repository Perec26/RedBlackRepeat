package com.pepekprodakshn.frame.ui.finishConfirm

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.ButtonDescription
import com.pepekprodakshn.designsystem.widgets.ScreenPreviews
import com.pepekprodakshn.designsystem.widgets.ThreeButtonsDialog
import com.pepekprodakshn.frame.R

@Composable
internal fun FinishConfirmDialog(
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
) {
    ThreeButtonsDialog(
        title = stringResource(id = R.string.frame_finish_frame_dialog_title),
        description = stringResource(id = R.string.frame_finish_frame_dialog_description),
        okButtonDescription = ButtonDescription(
            text = stringResource(id = R.string.frame_finish_frame_dialog_yes),
            onClick = onConfirmClick,
        ),
        noButtonDescription = ButtonDescription(
            text = stringResource(id = R.string.frame_finish_frame_dialog_no),
            onClick = onDismissClick,
        ),
        onDismissRequest = onDismissClick,
    )
}

@Composable
@ScreenPreviews
private fun FinishConfirmDialogPreview() {
    RedBlackRepeatTheme {
        FinishConfirmDialog(
            onConfirmClick = {},
            onDismissClick = {},
        )
    }
}