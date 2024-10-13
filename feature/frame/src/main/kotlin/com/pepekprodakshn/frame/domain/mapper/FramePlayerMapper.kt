package com.pepekprodakshn.frame.domain.mapper

import com.pepekprodakshn.frame.ui.PlayerUI
import com.pepekprodakshn.player.model.Player

internal fun List<PlayerUI>.toDomain() = map(PlayerUI::toDomain)

internal fun List<Player>.toUI() = map(Player::toUI)

internal fun PlayerUI.toDomain() = Player(
    name = name,
)

internal fun Player.toUI() = PlayerUI(
    id = id ?: 0,
    name = name,
)
