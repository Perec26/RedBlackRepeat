package com.pepekprodakshn.redblackrepeat.frame.domain

import com.pepekprodakshn.redblackrepeat.frame.data.FrameRepository
import com.pepekprodakshn.redblackrepeat.frame.domain.mapper.toDTO
import com.pepekprodakshn.redblackrepeat.frame.ui.model.FoulUI
import javax.inject.Inject

class FoulUseCase @Inject constructor(
    private val repository: FrameRepository,
) {

    fun execute(foulUI: FoulUI) = repository.foul(foulUI.toDTO())
}