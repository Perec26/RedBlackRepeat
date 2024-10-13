package com.pepekprodakshn.frame.domain

import com.pepekprodakshn.frame.data.FrameRepository
import com.pepekprodakshn.frame.domain.mapper.toDTO
import com.pepekprodakshn.frame.ui.model.FoulUI
import javax.inject.Inject

internal class FoulUseCase @Inject constructor(
    private val repository: FrameRepository,
) {

    fun execute(foulUI: FoulUI) {
        repository.foul(foulUI.toDTO())
    }
}