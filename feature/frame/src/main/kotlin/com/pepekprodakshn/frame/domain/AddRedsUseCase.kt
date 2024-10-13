package com.pepekprodakshn.frame.domain

import com.pepekprodakshn.frame.data.FrameRepository
import javax.inject.Inject

internal class AddRedsUseCase @Inject constructor(
    private val repository: FrameRepository,
) {

    fun execute(count: Int) {
        repository.addReds(count)
    }
}