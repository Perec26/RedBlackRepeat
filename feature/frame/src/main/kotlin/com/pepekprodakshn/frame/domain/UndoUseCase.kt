package com.pepekprodakshn.frame.domain

import com.pepekprodakshn.frame.data.FrameRepository
import javax.inject.Inject

internal class UndoUseCase @Inject constructor(private val repository: FrameRepository) {

    fun execute() {
        repository.undo()
    }
}
