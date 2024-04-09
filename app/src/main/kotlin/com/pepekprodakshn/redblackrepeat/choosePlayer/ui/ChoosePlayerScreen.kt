package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.base.ui.LANDSCAPE_DEVICE
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.CustomTopAppBar
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.choosePlayer.ui.widgets.PlayerItem
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun ChoosePlayerScreen(
    viewModel: ChoosePlayerViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    ChoosePlayerContent(
        state = state,
        onEvent = viewModel::onEvent,
    )
}

@Composable
private fun ChoosePlayerContent(
    state: ChoosePlayerViewState,
    onEvent: (ChoosePlayerEvent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            CustomTopAppBar(
                title = stringResource(R.string.choose_players_title),
                onNavigationClick = { onEvent(ChoosePlayerEvent.OnBackPressed) },
            ) {

                IconButton(onClick = { onEvent(ChoosePlayerEvent.OnAddPlayerClick) }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "",
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                items(state.players) {
                    PlayerItem(
                        player = it,
                        enabled = state.isEnabled || state.selectedPlayers.contains(it),
                        isSelected = state.selectedPlayers.contains(it),
                        onClick = { onEvent(ChoosePlayerEvent.OnPlayerClick(it)) },
                    )
                }
                item { SpacerHeight(height = 60.dp) }
                item { SpacerHeight(height = 60.dp) }
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomEnd),
            visible = !state.isEnabled,
            enter = scaleIn(),
            exit = scaleOut(),
        ) {

            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                onClick = { onEvent(ChoosePlayerEvent.OnStartMatchClick) },
            ) {

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "",
                )
            }
        }
    }

    if (state.showNewPlayerBottomSheet) {
        NewPlayerBottomSheet(
            name = state.newPlayerName,
            isError = state.isNewPlayerError,
            onEvent = onEvent,
        )
    }
}

@Preview(
    showBackground = true,
    device = LANDSCAPE_DEVICE,
)
@Composable
private fun ChoosePlayerScreenPreview() {
    ChoosePlayerScreenPreviewContent()
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = LANDSCAPE_DEVICE,
)
@Composable
private fun ChoosePlayerScreenPreviewDark() {
    ChoosePlayerScreenPreviewContent()
}

@Composable
private fun ChoosePlayerScreenPreviewContent() {
    RedBlackRepeatTheme {
        ChoosePlayerContent(state = choosePlayerViewStateMock) {}
    }
}