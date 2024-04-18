package com.pepekprodakshn.table.model

data class Foul(
    val points: Int,
    val isMiss: Boolean,
    val isFreeBall: Boolean,
    val removeReds: Int,
)