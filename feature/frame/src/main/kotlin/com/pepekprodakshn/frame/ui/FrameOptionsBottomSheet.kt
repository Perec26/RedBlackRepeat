package com.pepekprodakshn.frame.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.frame.ui.model.FrameOptionUI
import com.pepekprodakshn.frame.ui.widgets.FrameOptionGridItem

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

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        items(options) {
            FrameOptionGridItem(
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