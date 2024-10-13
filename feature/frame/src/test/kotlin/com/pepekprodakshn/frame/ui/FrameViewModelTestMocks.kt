package com.pepekprodakshn.frame.ui

import com.pepekprodakshn.frame.domain.AddRedsUseCase
import com.pepekprodakshn.frame.domain.EndBreakUseCase
import com.pepekprodakshn.frame.domain.FoulUseCase
import com.pepekprodakshn.frame.domain.GetPlayerUseCase
import com.pepekprodakshn.frame.domain.GetTableState
import com.pepekprodakshn.frame.domain.PotBallUseCase
import com.pepekprodakshn.frame.domain.RemoveRedsUseCase
import com.pepekprodakshn.frame.domain.RestartUseCase
import com.pepekprodakshn.frame.domain.UndoUseCase
import com.pepekprodakshn.frame.ui.model.FoulUI
import com.pepekprodakshn.navigation.RBRNavController
import io.mockk.mockk

internal val testFirstPlayerUI = PlayerUI(1, "test")
internal val testSecondPlayerUI = PlayerUI(2, "test2")
internal val testFoulUI = FoulUI()

internal val testNavController = mockk<RBRNavController>(relaxed = true)
internal val testGetPlayerUseCase = mockk<GetPlayerUseCase>(relaxed = true)
internal val testPotBallUseCase = mockk<PotBallUseCase>(relaxed = true)
internal val testEndBreakUseCase = mockk<EndBreakUseCase>()
internal val testUndoUseCase = mockk<UndoUseCase>(relaxed = true)
internal val testFoulUseCase = mockk<FoulUseCase>(relaxed = true)
internal val testRestartUseCase = mockk<RestartUseCase>(relaxed = true)
internal val testAddRedsUseCase = mockk<AddRedsUseCase>(relaxed = true)
internal val testRemoveRedsUseCase = mockk<RemoveRedsUseCase>(relaxed = true)

internal fun testFrameViewModel(
    navController: RBRNavController = mockk(relaxed = true),
    getPlayerUseCase: GetPlayerUseCase = mockk(relaxed = true),
    potBallUseCase: PotBallUseCase = mockk(relaxed = true),
    endBreakUseCase: EndBreakUseCase = mockk(relaxed = true),
    getTableState: GetTableState = mockk(relaxed = true),
    foulUseCase: FoulUseCase = mockk(relaxed = true),
    undoUseCase: UndoUseCase = mockk(relaxed = true),
    addRedsUseCase: AddRedsUseCase = mockk(relaxed = true),
    removeRedsUseCase: RemoveRedsUseCase = mockk(relaxed = true),
    restartUseCase: RestartUseCase = mockk(relaxed = true),
) = FrameViewModel(
    navController = navController,
    getPlayerUseCase = getPlayerUseCase,
    potBallUseCase = potBallUseCase,
    endBreakUseCase = endBreakUseCase,
    getTableState = getTableState,
    foulUseCase = foulUseCase,
    undoUseCase = undoUseCase,
    addRedsUseCase = addRedsUseCase,
    removeRedsUseCase = removeRedsUseCase,
    restartUseCase = restartUseCase,
)