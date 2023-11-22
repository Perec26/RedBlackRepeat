package com.pepekprodakshn.redblackrepeat.frame.ui.model

import androidx.compose.ui.graphics.Color
import com.pepekprodakshn.redblackrepeat.ui.theme.BallBlack
import com.pepekprodakshn.redblackrepeat.ui.theme.BallBlue
import com.pepekprodakshn.redblackrepeat.ui.theme.BallBrown
import com.pepekprodakshn.redblackrepeat.ui.theme.BallGreen
import com.pepekprodakshn.redblackrepeat.ui.theme.BallPink
import com.pepekprodakshn.redblackrepeat.ui.theme.BallRed
import com.pepekprodakshn.redblackrepeat.ui.theme.BallYellow

enum class BallUI(val value: Int, val color: Color, val textColor: Color) {
    RED(1, BallRed, Color.White),
    YELLOW(2, BallYellow, Color.Black),
    GREEN(3, BallGreen, Color.White),
    BROWN(4, BallBrown, Color.White),
    BLUE(5, BallBlue, Color.White),
    PINK(6, BallPink, Color.White),
    BLACK(7, BallBlack, Color.White),
}
