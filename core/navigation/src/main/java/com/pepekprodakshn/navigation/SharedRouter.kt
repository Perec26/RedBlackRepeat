package com.pepekprodakshn.navigation

interface SharedRouter {
    fun navigateToFrame(firstPlayerId: Int, secondPlayerId: Int)
    fun navigateToPlayerList()
}