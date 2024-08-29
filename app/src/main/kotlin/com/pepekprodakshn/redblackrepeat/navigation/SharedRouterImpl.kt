package com.pepekprodakshn.redblackrepeat.navigation

import com.pepekprodakshn.frame.ui.navigation.FrameDestinations
import com.pepekprodakshn.navigation.RBRNavController
import com.pepekprodakshn.navigation.SharedRouter
import com.pepekprodakshn.playerlist.ui.navigation.PlayerListDestinations
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedRouterImpl @Inject constructor(
    private val navController: RBRNavController,
) : SharedRouter {

    override fun navigateToFrame(
        firstPlayerId: Int,
        secondPlayerId: Int,
    ) {
        navController.navigateTo(
            destination = FrameDestinations.Frame,
            firstPlayerId,
            secondPlayerId,
        )
    }

    override fun navigateToPlayerList() {
        navController.navigateTo(PlayerListDestinations.ChoosePlayer)
    }
}