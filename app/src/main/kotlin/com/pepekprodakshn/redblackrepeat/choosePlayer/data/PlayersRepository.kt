package com.pepekprodakshn.redblackrepeat.choosePlayer.data

import com.pepekprodakshn.database.dao.PlayersDao
import com.pepekprodakshn.database.model.PlayerEntity
import javax.inject.Inject

class PlayersRepository @Inject constructor(
    private val dao: PlayersDao,
) {

    suspend fun getAllPlayers() = dao.getAll()

    suspend fun addPlayer(playerEntity: PlayerEntity) = dao.addPlayer(playerEntity)

    suspend fun getPlayer(id: Int) = dao.getPlayer(id)
}