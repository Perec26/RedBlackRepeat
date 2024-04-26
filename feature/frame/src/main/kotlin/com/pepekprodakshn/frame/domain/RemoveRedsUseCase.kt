package com.pepekprodakshn.frame.domain

import com.pepekprodakshn.frame.data.FrameRepository
import javax.inject.Inject

class RemoveRedsUseCase @Inject constructor(
    private val repository: FrameRepository,
) {

    fun execute(count: Int) = repository.removeReds(count)
}