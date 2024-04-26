package com.pepekprodakshn.frame.domain

import com.pepekprodakshn.frame.data.FrameRepository
import javax.inject.Inject

class EndBreakUseCase @Inject constructor(
    private val repository: FrameRepository,
) {

    fun execute() = repository.endBreak()
}