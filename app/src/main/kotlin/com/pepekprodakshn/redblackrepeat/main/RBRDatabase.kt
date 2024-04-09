package com.pepekprodakshn.redblackrepeat.main

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pepekprodakshn.redblackrepeat.choosePlayer.data.PlayersDao
import com.pepekprodakshn.redblackrepeat.choosePlayer.data.models.PlayerEntity

@Database(entities = [PlayerEntity::class], version = 1)
abstract class RBRDatabase : RoomDatabase() {

    abstract fun playersDao(): PlayersDao
}