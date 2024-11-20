package com.pepekprodakshn.frame.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pepekprodakshn.frame.ui.FrameScreen
import kotlinx.serialization.Serializable

@Serializable
data class Frame(val firstPlayerId: Int, val secondPlayerId: Int)

@Serializable
internal data object FinishCheck

fun NavGraphBuilder.frameNavigation(navController: NavHostController) {
    composable<Frame> { FrameScreen() }
}

fun NavController.navigateToFrame(frame: Frame) = navigate(frame)