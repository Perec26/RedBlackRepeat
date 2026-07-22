package com.pepekprodakshn.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val PLAYERS = "Players"

@Entity(tableName = PLAYERS)
data class PlayerEntity(@PrimaryKey(true) val id: Int? = null, val name: String)
