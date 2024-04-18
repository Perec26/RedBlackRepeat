package com.pepekprodakshn.redblackrepeat.frame.domain.mapper

import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallsStateUI
import com.pepekprodakshn.redblackrepeat.frame.ui.model.TableStateUI
import com.pepekprodakshn.table.model.TableState

fun TableState.toUI() = TableStateUI(
    firstPlayerPoints = firstPlayerPoints,
    secondPlayerPoints = secondPlayerPoints,
    ballState = BallsStateUI(
        redsCount = redsCount,
        lowestPriceBall = lowestPriceBall.toUI(),
        nextIsColor = nextIsColor,
        isFreeBall = frameBreak?.isFreeBall ?: false,
    ),
    breakUI = frameBreak?.toUI(),
    isFirstPlayerSelected = isFirstPlayerSelected,
)