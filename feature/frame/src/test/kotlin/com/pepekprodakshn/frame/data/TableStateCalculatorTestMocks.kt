package com.pepekprodakshn.frame.data

import com.pepekprodakshn.table.model.Ball
import com.pepekprodakshn.table.model.Foul
import com.pepekprodakshn.table.model.FrameBreak

internal val testFoul = Foul(
    points = 4,
    isMiss = false,
    isFreeBall = false,
    removeReds = 0,
)

internal val testBreakAllColors = FrameBreak(
    balls = listOf(
        Ball.YELLOW,
        Ball.GREEN,
        Ball.BROWN,
        Ball.BLUE,
        Ball.PINK,
        Ball.BLACK,
    ),
)
