package com.pepekprodakshn.redblackrepeat.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pepekprodakshn.redblackrepeat.frame.ui.FrameScreen
import com.pepekprodakshn.redblackrepeat.start.ui.StartScreen


@Composable
fun RedBlackRepeatApp() {
    val navController = rememberNavController()
    RedBlackRepeatNavHost(navController)
}

@Composable
fun RedBlackRepeatNavHost(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = "start"){
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