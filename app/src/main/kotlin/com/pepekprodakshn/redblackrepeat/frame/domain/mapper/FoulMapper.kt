package com.pepekprodakshn.redblackrepeat.frame.domain.mapper

import com.pepekprodakshn.redblackrepeat.frame.ui.model.FoulUI
import com.pepekprodakshn.table.model.Foul

fun FoulUI.toDTO() = Foul(
    points = points,
    isMiss = isMiss,
    isFreeBall = isFreeBall,
    removeReds = removeReds,
)