package com.pepekprodakshn.frame.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.pepekprodakshn.frame.ui.FrameNavigationEvent
import com.pepekprodakshn.frame.ui.FrameScreen
import com.pepekprodakshn.frame.ui.finishConfirm.FinishConfirmDialog
import kotlinx.serialization.Serializable

@Serializable
data class Frame(val firstPlayerId: Int, val secondPlayerId: Int)

@Serializable
data object FrameFinishConfirmation

fun NavGraphBuilder.frameNavigation(navController: NavHostController) {
    composable<Frame> {
        FrameScreen {
            when (it) {
                FrameNavigationEvent.OnBackPressed -> navController.navigate(FrameFinishConfirmation)
            }
        }
    }
    dialog<FrameFinishConfirmation> {
        FinishConfirmDialog(
            onConfirmClick = { navController.popBackStack<Frame>(inclusive = true) },
            onDismissClick = { navController.popBackStack() },
        )
    }
}

fun NavController.navigateToFrame(frame: Frame) = navigate(frame)