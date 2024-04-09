package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPlayerBottomSheet(
    name: String = "",
    isError: Boolean = false,
    onEvent: (ChoosePlayerEvent) -> Unit,
) {

    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        onDismissRequest = { onEvent(ChoosePlayerEvent.OnNewPlayerBottomSheetClosed) },
    ) {
        NewPlayerBottomSheetContent(name, isError, onEvent)
    }
}

@Composable
private fun NewPlayerBottomSheetContent(
    name: String = "",
    isError: Boolean = false,
    onEvent: (ChoosePlayerEvent) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(80.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape,
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = name.firstOrNull()?.uppercase() ?: "",
                style = RBRTypography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = name,
                isError = isError,
                supportingText = getErrorSupportingText(
                    isError = isError,
                    errorText = stringResource(R.string.new_player_validation_error),
                ),
                label = { Text(text = "Enter name") },
                onValueChange = { onEvent(ChoosePlayerEvent.OnNameChanged(it)) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        onEvent(ChoosePlayerEvent.OnNewPlayerDoneClick)
                    },
                ),
                singleLine = true,
            )

            IconButton(
                modifier = Modifier.padding(vertical = 8.dp),
                onClick = { onEvent(ChoosePlayerEvent.OnNewPlayerDoneClick) },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "",
                )
            }
        }
    }
}

private fun getErrorSupportingText(
    isError: Boolean,
    errorText: String,
): @Composable (() -> Unit)? {
    return if (isError) {
        { ErrorSupportingText(errorText) }
    } else {
        null
    }
}

@Composable
private fun ErrorSupportingText(text: String) {
    Text(
        text = text,
        style = RBRTypography.bodySmall,
        color = MaterialTheme.colorScheme.error,
    )
}

@PreviewLightDark
@Composable
private fun NewPlayerBottomSheetPreview() {
    RedBlackRepeatTheme {
        NewPlayerBottomSheetContent {}
    }
}