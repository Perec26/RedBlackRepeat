package com.pepekprodakshn.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pepekprodakshn.database.model.PlayerEntity

@Dao
interface PlayersDao {

    @Query("SELECT * FROM Players")
    suspend fun getAll(): List<PlayerEntity>

    @Query("SELECT * FROM Players WHERE id = :playerId LIMIT 1")
    suspend fun getPlayer(playerId: Int): PlayerEntity

    @Insert
    suspend fun addPlayer(playerEntity: PlayerEntity)
}
