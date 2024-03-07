package com.pepekprodakshn.redblackrepeat.frame.data

import javax.inject.Inject

class FrameRepository @Inject constructor(
    private val dataSource: FrameDataSource,
) {

    fun potBall(ballDTO: BallDTO) = dataSource.potBall(ballDTO)

    fun foul(foulDTO: FoulDTO) = dataSource.foul(foulDTO)

    fun undo() = dataSource.undoAction()

    fun addReds(count:Int) = dataSource.addReds(count)

    fun removeReds(count:Int) = dataSource.removeReds(count)

    fun endBreak() = dataSource.endBreak()

    fun getState() = dataSource.calculateState()

    fun restart() = dataSource.restart()
}