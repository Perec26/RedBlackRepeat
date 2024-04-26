package com.pepekprodakshn.frame.domain.mapper

import com.pepekprodakshn.frame.ui.model.BallUI
import com.pepekprodakshn.table.model.Ball

fun BallUI.toDTO() = when (this) {
    BallUI.RED -> Ball.RED
    BallUI.YELLOW -> Ball.YELLOW
    BallUI.GREEN -> Ball.GREEN
    BallUI.BROWN -> Ball.BROWN
    BallUI.BLUE -> Ball.BLUE
    BallUI.PINK -> Ball.PINK
    BallUI.BLACK -> Ball.BLACK
}

fun Ball.toUI() = when (this) {
    Ball.RED -> BallUI.RED
    Ball.YELLOW -> BallUI.YELLOW
    Ball.GREEN -> BallUI.GREEN
    Ball.BROWN -> BallUI.BROWN
    Ball.BLUE -> BallUI.BLUE
    Ball.PINK -> BallUI.PINK
    Ball.BLACK -> BallUI.BLACK
}

fun List<Ball>.toUI() = map(Ball::toUI)
