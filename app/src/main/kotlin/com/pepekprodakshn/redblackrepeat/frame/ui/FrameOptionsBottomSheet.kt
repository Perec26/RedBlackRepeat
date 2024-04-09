package com.pepekprodakshn.redblackrepeat.frame.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.pepekprodakshn.redblackrepeat.frame.ui.model.FrameOptionUI
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.FrameOption
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrameOptionsBottomSheet(
    options: List<FrameOptionUI>,
    onEvent: (FrameEvent) -> Unit,
) {

    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        onDismissRequest = { onEvent(FrameEvent.OnFrameOptionsBottomSheetClose) },
    ) {
        FrameOptionsBottomSheetContent(options, onEvent)
    }
}

@Composable
private fun FrameOptionsBottomSheetContent(
    options: List<FrameOptionUI>,
    onEvent: (FrameEvent) -> Unit,
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(options) {
            FrameOption(
                name = it.text,
                iconImageVector = it.icon,
                onClick = { onEvent(it.event) },
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun FrameOptionsBottomSheetPreview() {
    RedBlackRepeatTheme {
        FrameOptionsBottomSheetContent(
            FrameOptionUI.entries.toList(),
        ) {}
    }
}