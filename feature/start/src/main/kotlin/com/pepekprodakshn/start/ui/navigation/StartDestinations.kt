package com.pepekprodakshn.start.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pepekprodakshn.start.ui.StartNavigationEvent
import com.pepekprodakshn.start.ui.StartScreen
import kotlinx.serialization.Serializable

@Serializable
data object Start

fun NavGraphBuilder.startNavigation(
    onStartClick: () -> Unit,
) {
    composable<Start> {
        StartScreen {
            when (it) {
                is StartNavigationEvent.OnStartClick -> onStartClick()
            }
        }
    }
}