package com.pepekprodakshn.playerlist.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pepekprodakshn.playerlist.ui.ChoosePlayerNavigationEvent
import com.pepekprodakshn.playerlist.ui.ChoosePlayerScreen
import kotlinx.serialization.Serializable

@Serializable
data object PlayerList

fun NavGraphBuilder.playerListNavigation(
    navController: NavController,
    onStartFrameClick: (Int, Int) -> Unit,
) {
    composable<PlayerList> {
        ChoosePlayerScreen {
            when (it) {
                is ChoosePlayerNavigationEvent.OnStartFrameClick -> onStartFrameClick(
                    it.firstPlayerId,
                    it.secondPlayerId,
                )

                ChoosePlayerNavigationEvent.OnBackPress -> navController.navigateUp()
            }
        }
    }
}

fun NavController.navigateToPlayerList() = navigate(PlayerList)