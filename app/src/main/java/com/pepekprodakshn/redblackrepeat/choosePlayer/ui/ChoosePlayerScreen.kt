package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pepekprodakshn.redblackrepeat.base.ui.LANDSCAPE_DEVICE
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.CustomTopAppBar
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.choosePlayer.ui.widgets.PlayerItem
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun ChoosePlayerScreen(
    viewModel: ChoosePlayerViewModel = hiltViewModel(),
    onNavigation: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            CustomTopAppBar(
                title = "Players",
                onNavigationClick = { viewModel.onEvent(ChoosePlayerEvent.OnBackPressed) }
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(state.players) {
                    PlayerItem(
                        player = it,
                        enabled = state.isEnabled || state.selectedPlayers.contains(it),
                        isSelected = state.selectedPlayers.contains(it),
                        onClick = { viewModel.onEvent(ChoosePlayerEvent.OnPlayerClick(it)) }
                    )
                }
                item { SpacerHeight(height = 60.dp) }
                item { SpacerHeight(height = 60.dp) }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { viewModel.onEvent(ChoosePlayerEvent.OnAddPlayerClick) }
        ) {

            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = ""
            )

        }

    }

}

@Preview(
    showBackground = true,
    device = LANDSCAPE_DEVICE
)
@Composable
private fun ChoosePlayerScreenPreview() {
    ChoosePlayerScreenPreviewContent()
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = LANDSCAPE_DEVICE
)
@Composable
private fun ChoosePlayerScreenPreviewDark() {
    ChoosePlayerScreenPreviewContent()
}

@Composable
private fun ChoosePlayerScreenPreviewContent() {
    RedBlackRepeatTheme {
        ChoosePlayerScreen {}
    }
}