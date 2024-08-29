package com.pepekprodakshn.redblackrepeat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.pepekprodakshn.frame.ui.navigation.FrameDestinations
import com.pepekprodakshn.navigation.RBRNavController
import com.pepekprodakshn.navigation.getRoute
import com.pepekprodakshn.playerlist.ui.navigation.PlayerListDestinations

@Composable
fun RBRNavHost(navController: RBRNavController) {
    NavHost(navController = navController, startDestination = Destinations.Start.name) {
        getRoute(Destinations.Start)
        getRoute(PlayerListDestinations.ChoosePlayer)
        getRoute(FrameDestinations.Frame)
    }
}