package com.pepekprodakshn.redblackrepeat.frame.domain.mapper

import com.pepekprodakshn.redblackrepeat.frame.data.BreakDTO
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BreakUI

fun BreakDTO.toUI() = BreakUI(
    balls = balls.toUI(),
    isFreeBall = isFreeBall,
    freeBallScore = freeBallScore,
)