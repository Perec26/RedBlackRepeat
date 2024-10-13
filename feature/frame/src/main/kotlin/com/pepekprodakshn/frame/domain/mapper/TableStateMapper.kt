package com.pepekprodakshn.frame.domain.mapper

import com.pepekprodakshn.frame.ui.model.BallsStateUI
import com.pepekprodakshn.frame.ui.model.TableStateUI
import com.pepekprodakshn.table.model.TableState

internal fun TableState.toUI() = TableStateUI(
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