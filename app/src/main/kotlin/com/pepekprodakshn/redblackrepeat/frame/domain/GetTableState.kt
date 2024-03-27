package com.pepekprodakshn.redblackrepeat.frame.domain

import com.pepekprodakshn.redblackrepeat.frame.data.FrameRepository
import com.pepekprodakshn.redblackrepeat.frame.domain.mapper.toUI
import javax.inject.Inject

class GetTableState @Inject constructor(
    private val repository: FrameRepository,
) {

     fun execute() = repository.getState().toUI()
}