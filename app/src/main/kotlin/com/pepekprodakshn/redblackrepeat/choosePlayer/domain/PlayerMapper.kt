package com.pepekprodakshn.redblackrepeat.choosePlayer.domain

import com.pepekprodakshn.frame.ui.PlayerUI
import com.pepekprodakshn.player.model.Player

fun List<PlayerUI>.toDomain() = map(PlayerUI::toDomain)

fun List<Player>.toUI() = map(Player::toUI)

fun PlayerUI.toDomain() = Player(
    name = name,
)

fun Player.toUI() = PlayerUI(
    id = id ?: 0,
    name = name,
)
