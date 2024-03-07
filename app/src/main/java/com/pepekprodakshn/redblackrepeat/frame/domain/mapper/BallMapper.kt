package com.pepekprodakshn.redblackrepeat.frame.domain.mapper

import com.pepekprodakshn.redblackrepeat.frame.data.BallDTO
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallUI

fun BallUI.toDTO() = when (this) {
    BallUI.RED -> BallDTO.RED
    BallUI.YELLOW -> BallDTO.YELLOW
    BallUI.GREEN -> BallDTO.GREEN
    BallUI.BROWN -> BallDTO.BROWN
    BallUI.BLUE -> BallDTO.BLUE
    BallUI.PINK -> BallDTO.PINK
    BallUI.BLACK -> BallDTO.BLACK
}

fun BallDTO.toUI() = when (this) {
    BallDTO.RED -> BallUI.RED
    BallDTO.YELLOW -> BallUI.YELLOW
    BallDTO.GREEN -> BallUI.GREEN
    BallDTO.BROWN -> BallUI.BROWN
    BallDTO.BLUE -> BallUI.BLUE
    BallDTO.PINK -> BallUI.PINK
    BallDTO.BLACK -> BallUI.BLACK
}

fun List<BallDTO>.toUI() = map(BallDTO::toUI)
