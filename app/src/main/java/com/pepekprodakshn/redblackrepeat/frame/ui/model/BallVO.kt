package com.pepekprodakshn.redblackrepeat.frame.ui.model

import androidx.compose.ui.graphics.Color
import com.pepekprodakshn.redblackrepeat.ui.theme.BallBlack
import com.pepekprodakshn.redblackrepeat.ui.theme.BallBlue
import com.pepekprodakshn.redblackrepeat.ui.theme.BallBrown
import com.pepekprodakshn.redblackrepeat.ui.theme.BallGreen
import com.pepekprodakshn.redblackrepeat.ui.theme.BallPink
import com.pepekprodakshn.redblackrepeat.ui.theme.BallRed
import com.pepekprodakshn.redblackrepeat.ui.theme.BallYellow

enum class BallVO(val value: Int, val color: Color) {
    RED(1, BallRed),
    YELLOW(2, BallYellow),
    GREEN(3, BallGreen),
    BROWN(4, BallBrown),
    BLUE(5, BallBlue),
    PINK(6, BallPink),
    BLACK(7, BallBlack),
}
