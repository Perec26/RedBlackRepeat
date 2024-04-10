package com.pepekprodakshn.redblackrepeat.base.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun ThreeButtonsDialog(
    title: String,
    description: String,
    okButtonDescription: ButtonDescription? = null,
    noButtonDescription: ButtonDescription? = null,
    cancelButtonDescription: ButtonDescription? = null,
    onDismissRequest: () -> Unit,
) {
    DefaultDialog(onDismissRequest = onDismissRequest) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                text = title,
                style = RBRTypography.titleMedium,
            )

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = description,
                style = RBRTypography.bodyMedium,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                if (cancelButtonDescription != null) {
                    DefaultTextButton(
                        modifier = Modifier,
                        text = cancelButtonDescription.text,
                        onClick = cancelButtonDescription.onClick,
                    )
                } else {
                    SpacerWidth(width = 1.dp)
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    noButtonDescription?.let {
                        DefaultTextButton(
                            text = it.text,
                            onClick = it.onClick,
                        )
                    }

                    okButtonDescription?.let {
                        DefaultTextButton(
                            text = it.text,
                            onClick = it.onClick,
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ThreeButtonsDialogPreview() {
    RedBlackRepeatTheme {
        ThreeButtonsDialog(
            title = "Dialog title",
            description = "Dialog description",
            okButtonDescription = ButtonDescription("Ok"),
            noButtonDescription = ButtonDescription("No"),
            cancelButtonDescription = ButtonDescription("Cancel"),
        ) {}
    }
}

data class ButtonDescription(
    val text: String,
    val onClick: () -> Unit = {},
)