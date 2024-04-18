package com.pepekprodakshn.table.model

enum class Ball(val value: Int) {
    RED(1),
    YELLOW(2),
    GREEN(3),
    BROWN(4),
    BLUE(5),
    PINK(6),
    BLACK(7),
}

fun Ball.getNextValueBall() = when (this) {
    Ball.RED -> Ball.YELLOW
    Ball.YELLOW -> Ball.GREEN
    Ball.GREEN -> Ball.BROWN
    Ball.BROWN -> Ball.BLUE
    Ball.BLUE -> Ball.PINK
    Ball.PINK -> Ball.BLACK
    Ball.BLACK -> null
}