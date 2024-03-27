package com.pepekprodakshn.redblackrepeat.frame.domain

import com.pepekprodakshn.redblackrepeat.frame.data.FrameRepository
import com.pepekprodakshn.redblackrepeat.frame.domain.mapper.toDTO
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallUI
import javax.inject.Inject

class PotBallUseCase @Inject constructor(
    private val repository: FrameRepository,
) {

     fun execute(ballUI: BallUI) = repository.potBall(ballUI.toDTO())
}