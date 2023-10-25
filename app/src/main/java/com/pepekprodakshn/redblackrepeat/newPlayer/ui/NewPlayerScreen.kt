package com.pepekprodakshn.redblackrepeat.newPlayer.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pepekprodakshn.redblackrepeat.base.ui.LANDSCAPE_DEVICE
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPlayerScreen(
    viewModel: NewPlayerViewModel = viewModel(),
    onNavigation: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value

    ModalBottomSheet(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = {},
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = state.name.firstOrNull()?.uppercase() ?: "",
                    style = RBRTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            TextField(
                value = state.name,
                onValueChange = { viewModel.onEvent(NewPlayerEvent.OnNameChanged(it)) }
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = LANDSCAPE_DEVICE
)
@Composable
private fun NewPlayerScreenPreview() {
    NewPlayerScreenPreviewContent()
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = LANDSCAPE_DEVICE
)
@Composable
private fun NewPlayerScreenPreviewDark() {
    NewPlayerScreenPreviewContent()
}

@Composable
private fun NewPlayerScreenPreviewContent() {
    RedBlackRepeatTheme {
        NewPlayerScreen {}
    }
}