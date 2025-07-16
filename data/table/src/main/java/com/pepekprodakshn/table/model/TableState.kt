package com.pepekprodakshn.table.model

data class TableState(
    val firstPlayerPoints: Int = 0,
    val secondPlayerPoints: Int = 0,
    val redsCount: Int = 15,
    val lowestValueBall: Ball = Ball.RED,
    val nextIsColor: Boolean = false,
    val frameBreak: FrameBreak? = null,
    val isFirstPlayerSelected: Boolean = true,
)