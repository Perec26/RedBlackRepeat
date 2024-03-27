package com.pepekprodakshn.redblackrepeat.choosePlayer.domain

import com.pepekprodakshn.redblackrepeat.choosePlayer.data.models.PlayerEntity
import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI


fun List<PlayerUI>.toEntity() = map(PlayerUI::toEntity)

fun List<PlayerEntity>.toUI() = map(PlayerEntity::toUI)

fun PlayerUI.toEntity() = PlayerEntity(
    name = name
)

fun PlayerEntity.toUI() = PlayerUI(
    id = id ?: 0,
    name = name
)
