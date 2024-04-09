package com.pepekprodakshn.redblackrepeat.frame.domain.mapper

import com.pepekprodakshn.redblackrepeat.frame.data.TableStateDTO
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallsStateUI
import com.pepekprodakshn.redblackrepeat.frame.ui.model.TableStateUI

fun TableStateDTO.toUI() = TableStateUI(
    firstPlayerPoints = firstPlayerPoints,
    secondPlayerPoints = secondPlayerPoints,
    ballState = BallsStateUI(
        redsCount = redsCount,
        lowestPriceBall = lowestPriceBall.toUI(),
        nextIsColor = nextIsColor,
        isFreeBall = breakDTO?.isFreeBall ?: false,
    ),
    breakUI = breakDTO?.toUI(),
    isFirstPlayerSelected = isFirstPlayerSelected,
)