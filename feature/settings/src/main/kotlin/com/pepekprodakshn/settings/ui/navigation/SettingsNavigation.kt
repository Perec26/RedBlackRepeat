package com.pepekprodakshn.settings.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pepekprodakshn.settings.ui.SettingsNavigationEvent
import com.pepekprodakshn.settings.ui.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
data object Settings

fun NavGraphBuilder.settingsNavigation(onBackPress: () -> Unit = {}) {
    composable<Settings> {
        SettingsScreen {
            when (it) {
                SettingsNavigationEvent.OnBackPress -> onBackPress()
            }
        }
    }
}

fun NavController.navigateToSettings() = navigate(Settings)
