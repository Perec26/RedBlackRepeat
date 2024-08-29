package com.pepekprodakshn.playerlist.ui.navigation

import com.pepekprodakshn.navigation.Destination
import com.pepekprodakshn.playerlist.ui.ChoosePlayerScreen

object PlayerListDestinations {
    val ChoosePlayer = Destination("choosePlayer") { ChoosePlayerScreen() }
}