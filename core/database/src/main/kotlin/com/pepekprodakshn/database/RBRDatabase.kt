package com.pepekprodakshn.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pepekprodakshn.database.dao.PlayersDao
import com.pepekprodakshn.database.model.PlayerEntity

@Database(entities = [PlayerEntity::class], version = 1)
internal abstract class RBRDatabase : RoomDatabase() {

    abstract fun playersDao(): PlayersDao
}