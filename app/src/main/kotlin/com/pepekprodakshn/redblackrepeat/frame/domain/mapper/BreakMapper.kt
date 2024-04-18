package com.pepekprodakshn.redblackrepeat.frame.domain.mapper

import com.pepekprodakshn.redblackrepeat.frame.ui.model.BreakUI
import com.pepekprodakshn.table.model.FrameBreak

fun FrameBreak.toUI() = BreakUI(
    balls = balls.toUI(),
    isFreeBall = isFreeBall,
    freeBallScore = freeBallScore,
)