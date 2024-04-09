package com.pepekprodakshn.redblackrepeat.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.pepekprodakshn.redblackrepeat.choosePlayer.ui.ChoosePlayerScreen
import com.pepekprodakshn.redblackrepeat.frame.ui.FrameScreen
import com.pepekprodakshn.redblackrepeat.start.ui.StartScreen

const val FIRST_PLAYER_ID = "first_player_id"
const val SECOND_PLAYER_ID = "second_player_id"

object Destinations {
    val Start = Destination("start") { StartScreen() }
    val ChoosePlayer = Destination("choosePlayer") { ChoosePlayerScreen() }
    val Frame = Destination(
        name = "frame",
        arguments = listOf(
            navArgument(FIRST_PLAYER_ID) { type = NavType.IntType },
            navArgument(SECOND_PLAYER_ID) { type = NavType.IntType },
        ),
    ) { FrameScreen() }
}