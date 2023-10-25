package com.pepekprodakshn.redblackrepeat.choosePlayer.data

import com.pepekprodakshn.redblackrepeat.choosePlayer.data.models.PlayerEntity
import javax.inject.Inject

class PlayersRepository @Inject constructor(
    private val dao: PlayersDao,
) {

    suspend fun getAllPlayers() = dao.getAll()

    suspend fun addPlayer(playerEntity: PlayerEntity) = dao.addPlayer(playerEntity)
}