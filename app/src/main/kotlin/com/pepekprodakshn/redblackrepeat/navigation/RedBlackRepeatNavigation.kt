package com.pepekprodakshn.redblackrepeat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.pepekprodakshn.frame.ui.navigation.FrameDestinations
import com.pepekprodakshn.navigation.RBRNavController
import com.pepekprodakshn.navigation.getRoute
import com.pepekprodakshn.playerlist.ui.navigation.PlayerListDestinations
import com.pepekprodakshn.start.ui.navigation.StartDestinations

@Composable
fun RedBlackRepeatApp(navController: RBRNavController) {
    NavHost(navController = navController, startDestination = StartDestinations.Start.name) {
        getRoute(StartDestinations.Start)
        getRoute(PlayerListDestinations.ChoosePlayer)
        getRoute(FrameDestinations.Frame)
    }
}