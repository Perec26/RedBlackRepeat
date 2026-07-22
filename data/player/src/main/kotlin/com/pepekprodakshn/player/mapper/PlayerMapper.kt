package com.pepekprodakshn.player.mapper

import com.pepekprodakshn.database.model.PlayerEntity
import com.pepekprodakshn.player.model.Player

internal fun List<Player>.toEntity() = map(Player::toEntity)

internal fun List<PlayerEntity>.toDomain() = map(PlayerEntity::toDomain)

internal fun Player.toEntity() = PlayerEntity(

    name = name,
)

internal fun PlayerEntity.toDomain() = Player(
    id = id ?: 0,
    name = name,
)
