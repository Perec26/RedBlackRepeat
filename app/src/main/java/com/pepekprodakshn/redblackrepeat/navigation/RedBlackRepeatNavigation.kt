package com.pepekprodakshn.redblackrepeat.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pepekprodakshn.redblackrepeat.frame.ui.FrameScreen
import com.pepekprodakshn.redblackrepeat.start.ui.StartScreen


@Composable
fun RedBlackRepeatApp() {
    val navController = rememberNavController()
    Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        RedBlackRepeatNavHost(navController)
    }
}

@Composable
fun RedBlackRepeatNavHost(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(
                onNavigationEvent = {
                    navController.navigate("frame")
                }
            )
        }
        composable("frame") {
            FrameScreen(
                onNavigation = {}
            )
        }
    }
}