package com.pepekprodakshn.redblackrepeat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pepekprodakshn.frame.ui.navigation.Frame
import com.pepekprodakshn.frame.ui.navigation.frameNavigation
import com.pepekprodakshn.frame.ui.navigation.navigateToFrame
import com.pepekprodakshn.playerlist.ui.navigation.navigateToPlayerList
import com.pepekprodakshn.playerlist.ui.navigation.playerListNavigation
import com.pepekprodakshn.settings.ui.navigation.navigateToSettings
import com.pepekprodakshn.settings.ui.navigation.settingsNavigation
import com.pepekprodakshn.start.ui.navigation.Start
import com.pepekprodakshn.start.ui.navigation.startNavigation

@Composable
fun RedBlackRepeatApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Start) {
        startNavigation(
            onStartClick = navController::navigateToPlayerList,
            onSettingsClick = navController::navigateToSettings,
        )
        playerListNavigation(
            onBackPress = navController::navigateUp,
            onStartFrameClick = { firstPlayerId, secondPlayerId ->
                navController.navigateToFrame(Frame(firstPlayerId, secondPlayerId))
            },
        )
        frameNavigation(navController = navController)
        settingsNavigation(onBackPress = navController::navigateUp)
    }
}
