package com.pepekprodakshn.redblackrepeat.frame.data.model

import com.pepekprodakshn.redblackrepeat.frame.data.BallDTO
import com.pepekprodakshn.redblackrepeat.frame.data.BreakDTO
import com.pepekprodakshn.redblackrepeat.frame.data.FoulDTO
import com.pepekprodakshn.redblackrepeat.frame.data.TableStateDTO

val finalTableState = TableStateDTO(
    redsCount = 0,
    lowestPriceBall = BallDTO.YELLOW,
)

val preFinalTableState = TableStateDTO(
    redsCount = 0,
    lowestPriceBall = BallDTO.RED,
)

val startTableStateWithFreeBall = TableStateDTO(
    breakDTO = BreakDTO(isFreeBall = true),
)

val finalTableStateWithFreeBall = finalTableState.copy(
    breakDTO = BreakDTO(isFreeBall = true),
)

val foul = FoulDTO(
    points = 4,
    isMiss = false,
    isFreeBall = false,
    removeReds = 0,
)

val foulWithMiss = foul.copy(isMiss = true)

val foulWithFreeBall = foul.copy(isFreeBall = true)

val foulWithRemoveReds = foul.copy(removeReds = 1)