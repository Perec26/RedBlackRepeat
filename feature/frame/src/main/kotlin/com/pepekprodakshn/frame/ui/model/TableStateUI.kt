package com.pepekprodakshn.frame.ui.model

internal data class TableStateUI(
    val firstPlayerPoints: Int = 0,
    val secondPlayerPoints: Int = 0,
    val ballState: BallsStateUI = BallsStateUI(),
    val breakUI: BreakUI? = null,
    val isFirstPlayerSelected: Boolean = true,
)