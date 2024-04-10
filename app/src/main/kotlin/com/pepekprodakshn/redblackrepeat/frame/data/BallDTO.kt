package com.pepekprodakshn.redblackrepeat.frame.data

enum class BallDTO(val value: Int) {
    RED(1),
    YELLOW(2),
    GREEN(3),
    BROWN(4),
    BLUE(5),
    PINK(6),
    BLACK(7),
}

fun BallDTO.getNextValueBall() = when (this) {
    BallDTO.RED -> BallDTO.YELLOW
    BallDTO.YELLOW -> BallDTO.GREEN
    BallDTO.GREEN -> BallDTO.BROWN
    BallDTO.BROWN -> BallDTO.BLUE
    BallDTO.BLUE -> BallDTO.PINK
    BallDTO.PINK -> BallDTO.BLACK
    BallDTO.BLACK -> null
}