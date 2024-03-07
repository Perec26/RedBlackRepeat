package com.pepekprodakshn.redblackrepeat.choosePlayer.ui

import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI

val listOfPlayers = listOf(
    PlayerUI(id = 1, name = "Ronnie O'Sullivan"),
    PlayerUI(id = 2, name = "Luca Brecel"),
    PlayerUI(id = 3, name = "Judd Trump"),
    PlayerUI(id = 4, name = "Mark Allen"),
    PlayerUI(id = 5, name = "Mark Selby"),
    PlayerUI(id = 6, name = "Neil Robertson"),
    PlayerUI(id = 7, name = "Shaun Murphy"),
    PlayerUI(id = 8, name = "Kyren Wilson"),
    PlayerUI(id = 9, name = "Mark J Williams"),
)

val choosePlayerViewStateMock = ChoosePlayerViewState(
    players = listOfPlayers,
)