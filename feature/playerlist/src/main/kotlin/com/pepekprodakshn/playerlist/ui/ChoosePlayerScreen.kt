package com.pepekprodakshn.playerlist.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pepekprodakshn.designsystem.isPortrait
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.CustomTopAppBar
import com.pepekprodakshn.designsystem.widgets.DefaultFilledButton
import com.pepekprodakshn.designsystem.widgets.ScreenPreviews
import com.pepekprodakshn.designsystem.widgets.SpacerHeight
import com.pepekprodakshn.playerlist.R
import com.pepekprodakshn.playerlist.ui.widgets.PlayerItem

@Composable
internal fun ChoosePlayerScreen(
    viewModel: ChoosePlayerViewModel = hiltViewModel(),
    onStartFrameClick: (Int, Int) -> Unit = { _, _ -> },
    onBackPress: () -> Unit = {},
) {
    val state = viewModel.state.collectAsState().value

    ChoosePlayerContent(
        state = state,
        onStartFrameClick = onStartFrameClick,
        onBackPress = onBackPress,
        onEvent = viewModel::onEvent,
    )
}

@Composable
private fun ChoosePlayerContent(
    state: ChoosePlayerViewState,
    onStartFrameClick: (Int, Int) -> Unit = { _, _ -> },
    onBackPress: () -> Unit = {},
    onEvent: (ChoosePlayerEvent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {

            CustomTopAppBar(
                title = stringResource(R.string.choose_players_title),
                onNavigationClick = onBackPress,
            ) {

                IconButton(onClick = { onEvent(ChoosePlayerEvent.OnAddPlayerClick) }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "",
                    )
                }
            }

            if (state.players.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(R.string.choose_players_empty_players_title),
                        style = MaterialTheme.typography.titleLarge,
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                        text = stringResource(R.string.choose_players_empty_players_description),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall,
                    )

                    DefaultFilledButton(
                        text = stringResource(R.string.choose_players_empty_players_button),
                        onClick = { onEvent(ChoosePlayerEvent.OnAddPlayerClick) },
                    )
                }
            }

            val columnsNumber = if (isPortrait()) 1 else 2

            LazyVerticalGrid(
                columns = GridCells.Fixed(columnsNumber),
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
                onClick = {
                    onStartFrameClick(
                        state.selectedPlayers.first().id,
                        state.selectedPlayers.last().id,
                    )
                },
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

@ScreenPreviews
@Composable
private fun ChoosePlayerScreenPreview() {
    ChoosePlayerScreenPreviewContent()
}

@ScreenPreviews
@Composable
private fun ChoosePlayerScreenEmptyPreview() {
    ChoosePlayerScreenPreviewContent(true)
}

@Composable
private fun ChoosePlayerScreenPreviewContent(isEmpty: Boolean = false) {
    RedBlackRepeatTheme {
        ChoosePlayerContent(
            state = choosePlayerViewStateMock.copy(
                players = if (isEmpty) emptyList() else listOfPlayers,
            ),
        ) {}
    }
}