package com.pepekprodakshn.redblackrepeat.choosePlayer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pepekprodakshn.redblackrepeat.choosePlayer.data.models.PlayerEntity

@Dao
interface PlayersDao {

    @Query("SELECT * FROM Players")
    suspend fun getAll(): List<PlayerEntity>

    @Insert
    suspend fun addPlayer(playerEntity: PlayerEntity)
}