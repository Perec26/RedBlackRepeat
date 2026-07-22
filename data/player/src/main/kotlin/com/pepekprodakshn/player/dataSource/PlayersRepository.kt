package com.pepekprodakshn.player.dataSource

import com.pepekprodakshn.database.dao.PlayersDao
import com.pepekprodakshn.player.mapper.toDomain
import com.pepekprodakshn.player.mapper.toEntity
import com.pepekprodakshn.player.model.Player
import javax.inject.Inject

class PlayersRepository @Inject constructor(private val dao: PlayersDao) {

    suspend fun getAllPlayers() = dao.getAll().toDomain()

    suspend fun addPlayer(player: Player) = dao.addPlayer(player.toEntity())

    suspend fun getPlayer(id: Int) = dao.getPlayer(id).toDomain()
}
