package com.pepekprodakshn.playerlist.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.pepekprodakshn.designsystem.R.drawable
import com.pepekprodakshn.designsystem.isPortrait
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.CustomTopAppBar
import com.pepekprodakshn.designsystem.widgets.DefaultFilledButton
import com.pepekprodakshn.designsystem.widgets.ScreenPreviews
import com.pepekprodakshn.designsystem.widgets.SpacerHeight
import com.pepekprodakshn.playerlist.R
import com.pepekprodakshn.playerlist.ui.newPlayer.NewPlayerBottomSheet
import com.pepekprodakshn.playerlist.ui.widgets.PlayerItem
import com.pepekprodakshn.ui.ListState

@Composable
internal fun ChoosePlayerScreen(
    viewModel: ChoosePlayerViewModel = hiltViewModel(),
    navigationHandler: (ChoosePlayerNavigationEvent) -> Unit = {},
) {
    val state = viewModel.state.collectAsState().value
    LaunchedEffect(true) { viewModel.navigationEvent.collect(navigationHandler) }

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
    Scaffold(
        topBar = { TopAppBar(onEvent = onEvent) },
        floatingActionButton = {
            FloatingActionButton(
                onEvent = onEvent,
                isEnabled = !state.isEnabled,
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = MaterialTheme.colorScheme.surface,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                when (state.listState) {
                    ListState.LOADING -> LoadingPlayerList()
                    ListState.EMPTY -> EmptyPlayerList(onEvent)
                    ListState.READY -> PlayerList(state, onEvent)
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
}

@Composable
private fun EmptyPlayerList(onEvent: (ChoosePlayerEvent) -> Unit) {
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

@Composable
private fun TopAppBar(onEvent: (ChoosePlayerEvent) -> Unit) {
    CustomTopAppBar(
        title = stringResource(R.string.choose_players_title),
        onNavigationClick = { onEvent(ChoosePlayerEvent.OnBackPressed) },
    ) {
        IconButton(onClick = { onEvent(ChoosePlayerEvent.OnAddPlayerClick) }) {
            Icon(
                painter = painterResource(drawable.add_24),
                contentDescription = "",
            )
        }
    }
}

@Composable
private fun FloatingActionButton(isEnabled: Boolean, onEvent: (ChoosePlayerEvent) -> Unit) {
    AnimatedVisibility(
        visible = isEnabled,
        enter = scaleIn(),
        exit = scaleOut(),
    ) {
        FloatingActionButton(
            modifier = Modifier.padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            onClick = { onEvent(ChoosePlayerEvent.OnStartFrameClick) },
        ) {
            Icon(
                painter = painterResource(drawable.arrow_forward_24),
                contentDescription = "",
            )
        }
    }
}

@Composable
private fun ColumnScope.PlayerList(
    state: ChoosePlayerViewState,
    onEvent: (ChoosePlayerEvent) -> Unit,
) {
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

@Composable
private fun LoadingPlayerList() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
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
    ChoosePlayerScreenPreviewContent(ListState.EMPTY)
}

@ScreenPreviews
@Composable
private fun ChoosePlayerScreenLoadingPreview() {
    ChoosePlayerScreenPreviewContent(ListState.LOADING)
}

@Composable
private fun ChoosePlayerScreenPreviewContent(listState: ListState = ListState.READY) {
    RedBlackRepeatTheme {
        ChoosePlayerContent(
            state = choosePlayerViewStateMock.copy(
                listState = listState,
            ),
        ) {}
    }
}
