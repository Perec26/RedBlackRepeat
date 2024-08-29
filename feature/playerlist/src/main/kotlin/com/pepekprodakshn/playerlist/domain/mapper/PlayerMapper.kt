package com.pepekprodakshn.playerlist.domain.mapper

import com.pepekprodakshn.player.model.Player
import com.pepekprodakshn.playerlist.ui.model.PlayerUI

fun List<PlayerUI>.toDomain() = map(PlayerUI::toDomain)

fun List<Player>.toUI() = map(Player::toUI)

fun PlayerUI.toDomain() = Player(
    name = name,
)

fun Player.toUI() = PlayerUI(
    id = id ?: 0,
    name = name,
)
