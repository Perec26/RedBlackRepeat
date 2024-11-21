package com.pepekprodakshn.playerlist.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pepekprodakshn.playerlist.ui.ChoosePlayerScreen
import kotlinx.serialization.Serializable

@Serializable
data object PlayerList

fun NavGraphBuilder.playerListNavigation(
    navController: NavController,
    onStartFrameClick: (Int, Int) -> Unit,
) {
    composable<PlayerList> {
        ChoosePlayerScreen(
            onStartFrameClick = onStartFrameClick,
            onBackPress = { navController.navigateUp() },
        )
    }
}

fun NavController.navigateToPlayerList() = navigate(PlayerList)