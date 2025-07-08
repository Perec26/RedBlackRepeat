package com.pepekprodakshn.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.designsystem.widgets.CustomTopAppBar
import com.pepekprodakshn.designsystem.widgets.ScreenPreviews
import com.pepekprodakshn.settings.R
import com.pepekprodakshn.settings.ui.widgets.SettingElement

@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navigationHandler: (SettingsNavigationEvent) -> Unit = {},
) {
    val state = viewModel.state.collectAsState().value
    LaunchedEffect(true) { viewModel.navigationEvent.collect(navigationHandler) }

    SettingsScreenContent(
        state = state,
        onEvent = viewModel::onEvent,
    )
}

@Composable
private fun SettingsScreenContent(
    state: SettingsUiState,
    onEvent: (SettingsEvent) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(onEvent = onEvent) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
        ) {
            SettingElement(
                title = stringResource(R.string.settings_use_system_theme),
                value = state.useSystemTheme,
                onClick = { onEvent(SettingsEvent.OnUseSystemThemeClick) },
            )
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            SettingElement(
                title = stringResource(R.string.settings_use_dark_theme),
                value = state.useDarkTheme,
                enabled = state.useDarkThemeSwitchEnabled,
                onClick = { onEvent(SettingsEvent.OnUseDarkThemeClick) },
            )
        }
    }
}

@Composable
private fun TopAppBar(
    onEvent: (SettingsEvent) -> Unit,
) {
    CustomTopAppBar(
        title = stringResource(R.string.settings_title),
        onNavigationClick = { onEvent(SettingsEvent.OnBackPressed) },
    ) {
    }
}

@ScreenPreviews
@Composable
private fun SettingsPreviewDark() {
    SettingsScreenPreviewContent()
}

@Composable
private fun SettingsScreenPreviewContent() {
    RedBlackRepeatTheme {
        SettingsScreenContent(state = SettingsUiState()) {}
    }
}