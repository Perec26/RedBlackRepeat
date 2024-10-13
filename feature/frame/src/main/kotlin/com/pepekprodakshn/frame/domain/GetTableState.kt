package com.pepekprodakshn.frame.domain

import com.pepekprodakshn.frame.data.FrameRepository
import com.pepekprodakshn.frame.domain.mapper.toUI
import javax.inject.Inject

internal class GetTableState @Inject constructor(
    private val repository: FrameRepository,
) {

    fun execute() = repository.getState().toUI()
}