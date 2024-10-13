package com.pepekprodakshn.frame.domain

import com.pepekprodakshn.frame.data.FrameRepository
import javax.inject.Inject

internal class RestartUseCase @Inject constructor(
    private val repository: FrameRepository,
) {

    fun execute() = repository.restart()
}