package com.pepekprodakshn.redblackrepeat.frame.data.model

import com.pepekprodakshn.table.model.Ball
import com.pepekprodakshn.table.model.Foul
import com.pepekprodakshn.table.model.FrameBreak
import com.pepekprodakshn.table.model.TableState

val finalTableState = TableState(
    redsCount = 0,
    lowestPriceBall = Ball.YELLOW,
)

val preFinalTableState = TableState(
    redsCount = 0,
    lowestPriceBall = Ball.RED,
)

val startTableStateWithFreeBall = TableState(
    frameBreak = FrameBreak(isFreeBall = true),
)

val finalTableStateWithFreeBall = finalTableState.copy(
    frameBreak = FrameBreak(isFreeBall = true),
)

val foul = Foul(
    points = 4,
    isMiss = false,
    isFreeBall = false,
    removeReds = 0,
)

val foulWithMiss = foul.copy(isMiss = true)

val foulWithFreeBall = foul.copy(isFreeBall = true)

val foulWithRemoveReds = foul.copy(removeReds = 1)