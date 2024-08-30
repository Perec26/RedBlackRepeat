package com.pepekprodakshn.start.ui.navigation

import com.pepekprodakshn.navigation.Destination
import com.pepekprodakshn.start.ui.StartScreen

object StartDestinations {
    val Start = Destination("start") { StartScreen() }
}