package com.pepekprodakshn.redblackrepeat.frame.domain.mapper

import com.pepekprodakshn.redblackrepeat.frame.data.FoulDTO
import com.pepekprodakshn.redblackrepeat.frame.ui.model.FoulUI

fun FoulUI.toDTO() = FoulDTO(
    points = points,
    isMiss = isMiss,
    isFreeBall = isFreeBall,
    removeReds = removeReds
)