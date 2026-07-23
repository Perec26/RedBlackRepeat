package com.pepekprodakshn.start.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.pepekprodakshn.designsystem.R as designR
import com.pepekprodakshn.designsystem.isPortrait
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.ScreenPreviews
import com.pepekprodakshn.designsystem.widgets.SpacerHeight
import com.pepekprodakshn.start.R

@Composable
internal fun StartScreen(
    viewModel: StartViewModel = hiltViewModel(),
    navigationHandler: (StartNavigationEvent) -> Unit = {},
) {
    val state = viewModel.state.collectAsState().value
    LaunchedEffect(Unit) { viewModel.navigationEvent.collect(navigationHandler) }

    StartScreenContent(state = state, onEvent = viewModel::onEvent)
}

@Composable
private fun StartScreenContent(state: StartUiState, onEvent: (StartEvent) -> Unit) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = MaterialTheme.colorScheme.surface,
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
        ) {
            if (isPortrait()) {
                StartScreenContentPortrait(onEvent)
            } else {
                StartScreenContentLandscape(onEvent)
            }

            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(32.dp),
                text = state.version,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun StartScreenContentPortrait(onEvent: (StartEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
    ) {
        Logo()
        Buttons(onEvent)
    }
}

@Composable
private fun StartScreenContentLandscape(onEvent: (StartEvent) -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(64.dp, Alignment.CenterHorizontally),
    ) {
        Logo()
        Buttons(onEvent)
    }
}

@Composable
private fun Logo() {
    Image(
        modifier = Modifier.sizeIn(
            minWidth = 120.dp,
            minHeight = 120.dp,
            maxHeight = 200.dp,
            maxWidth = 200.dp
        ).fillMaxSize(),
        painter = painterResource(designR.drawable.logo),
        contentDescription = null,
    )
}

@Composable
private fun Buttons(onEvent: (StartEvent) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val size = ButtonDefaults.MediumContainerHeight
        Button(

            onClick = { onEvent(StartEvent.OnStartClick) },
            contentPadding = ButtonDefaults.contentPaddingFor(size)
        ) {
            Text(
                text = stringResource(R.string.start_start),
                style = ButtonDefaults.textStyleFor(size)
            )
        }

        SpacerHeight(8.dp)

        TextButton(onClick = { onEvent(StartEvent.OnSettingsClick) }) {
            Text(text = stringResource(R.string.start_settings))
        }
    }
}

@ScreenPreviews
@Composable
private fun StartScreenPreview() {
    RedBlackRepeatTheme {
        StartScreenContent(StartUiState()) {}
    }
}
