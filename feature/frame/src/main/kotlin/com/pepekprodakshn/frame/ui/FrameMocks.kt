package com.pepekprodakshn.frame.ui

import com.pepekprodakshn.frame.ui.model.BallUI
import com.pepekprodakshn.frame.ui.model.BallsStateUI
import com.pepekprodakshn.frame.ui.model.BreakUI
import com.pepekprodakshn.frame.ui.model.TableStateUI

internal val firstPlayerUIMock = PlayerUI(
    id = 1,
    name = "Ronnie O'Sullivan",
)

internal val secondPlayerUIMock = PlayerUI(
    id = 2,
    name = "Mark Selby",
)

internal val ballsState = BallsStateUI()

internal val breakMock = BreakUI(
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

internal val frameUiStateMock = FrameUiState(
    firstPlayerUI = firstPlayerUIMock,
    secondPlayerUI = secondPlayerUIMock,
    tableState = TableStateUI(breakUI = breakMock),
)