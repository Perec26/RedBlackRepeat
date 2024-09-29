package com.pepekprodakshn.playerlist.ui

import com.pepekprodakshn.navigation.RBRNavController
import com.pepekprodakshn.navigation.SharedRouter
import com.pepekprodakshn.playerlist.domain.AddPlayerUseCase
import com.pepekprodakshn.playerlist.domain.GetAllPlayersUseCase
import com.pepekprodakshn.playerlist.domain.ValidateNameUseCase
import io.mockk.mockk

internal val testNavController = mockk<RBRNavController>(relaxed = true)
internal val testValidateNameUseCase = mockk<ValidateNameUseCase>()
internal val testAddPlayerUseCase = mockk<AddPlayerUseCase>(relaxed = true)
internal val testSharedRouter = mockk<SharedRouter>(relaxed = true)

internal fun testViewModel(
    getAllPlayersUseCase: GetAllPlayersUseCase = mockk<GetAllPlayersUseCase>(relaxed = true),
    validateNameUseCase: ValidateNameUseCase = mockk<ValidateNameUseCase>(relaxed = true),
    addPlayerUseCase: AddPlayerUseCase = mockk<AddPlayerUseCase>(relaxed = true),
    sharedRouter: SharedRouter = mockk<SharedRouter>(relaxed = true),
    navController: RBRNavController = mockk<RBRNavController>(relaxed = true),
) = ChoosePlayerViewModel(
    getAllPlayersUseCase = getAllPlayersUseCase,
    validateNameUseCase = validateNameUseCase,
    addPlayerUseCase = addPlayerUseCase,
    sharedRouter = sharedRouter,
    navController = navController,
)