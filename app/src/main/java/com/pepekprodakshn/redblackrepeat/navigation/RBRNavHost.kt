package com.pepekprodakshn.redblackrepeat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

@Composable
fun RBRNavHost(navController: RBRNavController) {
    NavHost(navController = navController, startDestination = Destinations.Start.name) {
        getRoute(Destinations.Start)
        getRoute(Destinations.ChoosePlayer)
        getRoute(Destinations.Frame)
    }
}