package com.pepekprodakshn.frame.ui

import com.pepekprodakshn.frame.ui.model.BallUI
import com.pepekprodakshn.frame.ui.model.BallsStateUI
import com.pepekprodakshn.frame.ui.model.BreakUI
import com.pepekprodakshn.frame.ui.model.TableStateUI

val firstPlayerUIMock = PlayerUI(
    id = 1,
    name = "Ronnie O'Sullivan",
)

val secondPlayerUIMock = PlayerUI(
    id = 2,
    name = "Mark Selby",
)

val ballsState = BallsStateUI()

val breakMock = BreakUI(
    balls = listOf(
        BallUI.RED,
        BallUI.RED,
        BallUI.BLACK,
        BallUI.RED,
        BallUI.YELLOW,
        BallUI.BROWN,
        BallUI.GREEN,
        BallUI.PINK,
        BallUI.BLUE,
    ),
)

val frameUiStateMock = FrameUiState(
    firstPlayerUI = firstPlayerUIMock,
    secondPlayerUI = secondPlayerUIMock,
    tableState = TableStateUI(breakUI = breakMock),
)