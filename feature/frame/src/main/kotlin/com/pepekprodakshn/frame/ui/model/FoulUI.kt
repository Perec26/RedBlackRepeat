package com.pepekprodakshn.frame.ui.model

internal const val LOWEST_FOUL_VALUE = 4

internal data class FoulUI(
    val points: Int = 0,
    val isMiss: Boolean = false,
    val isFreeBall: Boolean = false,
    val removeReds: Int = 0,
    val canAddReds: Boolean = true,
    val showRemoveReds: Boolean = true,
    val lowestBallValue: Int = LOWEST_FOUL_VALUE,
)
