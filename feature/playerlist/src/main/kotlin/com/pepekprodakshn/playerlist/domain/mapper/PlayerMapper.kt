package com.pepekprodakshn.playerlist.domain.mapper

import com.pepekprodakshn.player.model.Player
import com.pepekprodakshn.playerlist.ui.model.PlayerUI

internal fun List<PlayerUI>.toDomain() = map(PlayerUI::toDomain)

internal fun List<Player>.toUI() = map(Player::toUI)

internal fun PlayerUI.toDomain() = Player(
    name = name,
)

internal fun Player.toUI() = PlayerUI(
    id = id ?: 0,
    name = name,
)
