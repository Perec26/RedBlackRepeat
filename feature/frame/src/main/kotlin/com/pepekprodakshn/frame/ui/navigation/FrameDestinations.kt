package com.pepekprodakshn.frame.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.pepekprodakshn.frame.ui.FrameScreen
import com.pepekprodakshn.navigation.Destination

const val FIRST_PLAYER_ID = "first_player_id"
const val SECOND_PLAYER_ID = "second_player_id"

object FrameDestinations {
    val Frame = Destination(
        name = "frame",
        arguments = listOf(
            navArgument(FIRST_PLAYER_ID) { type = NavType.IntType },
            navArgument(SECOND_PLAYER_ID) { type = NavType.IntType },
        ),
    ) { FrameScreen() }
}