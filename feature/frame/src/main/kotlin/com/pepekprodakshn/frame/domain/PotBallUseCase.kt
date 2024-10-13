package com.pepekprodakshn.frame.domain

import com.pepekprodakshn.frame.data.FrameRepository
import com.pepekprodakshn.frame.domain.mapper.toDTO
import com.pepekprodakshn.frame.ui.model.BallUI
import javax.inject.Inject

internal class PotBallUseCase @Inject constructor(
    private val repository: FrameRepository,
) {

    fun execute(ballUI: BallUI) = repository.potBall(ballUI.toDTO())
}