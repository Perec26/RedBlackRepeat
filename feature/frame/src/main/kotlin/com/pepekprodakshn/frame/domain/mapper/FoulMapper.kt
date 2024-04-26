package com.pepekprodakshn.frame.domain.mapper

import com.pepekprodakshn.frame.ui.model.FoulUI
import com.pepekprodakshn.table.model.Foul

fun FoulUI.toDTO() = Foul(
    points = points,
    isMiss = isMiss,
    isFreeBall = isFreeBall,
    removeReds = removeReds,
)