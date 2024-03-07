package com.pepekprodakshn.redblackrepeat.frame.domain

import com.pepekprodakshn.redblackrepeat.frame.data.FrameRepository
import javax.inject.Inject

class RemoveRedsUseCase @Inject constructor(
    private val repository: FrameRepository,
) {

    fun execute(count: Int) = repository.removeReds(count)
}