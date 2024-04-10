package com.pepekprodakshn.redblackrepeat.frame.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.pepekprodakshn.redblackrepeat.R

enum class BallUI(
    val value: Int,
    @DrawableRes val icon: Int,
    val textColor: Color,
) {
    RED(1, R.drawable.ic_ball_full_red, Color.White),
    YELLOW(2, R.drawable.ic_ball_full_yellow, Color.Black),
    GREEN(3, R.drawable.ic_ball_full_green, Color.White),
    BROWN(4, R.drawable.ic_ball_full_brown, Color.White),
    BLUE(5, R.drawable.ic_ball_full_blue, Color.White),
    PINK(6, R.drawable.ic_ball_full_pink, Color.White),
    BLACK(7, R.drawable.ic_ball_full_black, Color.White),
}
