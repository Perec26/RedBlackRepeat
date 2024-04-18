package com.pepekprodakshn.player.mapper

import com.pepekprodakshn.database.model.PlayerEntity
import com.pepekprodakshn.player.model.Player

fun List<Player>.toEntity() = map(Player::toEntity)

fun List<PlayerEntity>.toDomain() = map(PlayerEntity::toDomain)

fun Player.toEntity() = PlayerEntity(
    name = name,
)

fun PlayerEntity.toDomain() = Player(
    id = id ?: 0,
    name = name,
)
