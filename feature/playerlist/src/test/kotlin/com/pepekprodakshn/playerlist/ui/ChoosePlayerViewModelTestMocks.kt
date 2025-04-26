package com.pepekprodakshn.playerlist.ui

import com.pepekprodakshn.playerlist.domain.AddPlayerUseCase
import com.pepekprodakshn.playerlist.domain.GetAllPlayersUseCase
import com.pepekprodakshn.playerlist.domain.ValidateNameUseCase
import com.pepekprodakshn.playerlist.ui.model.PlayerUI
import io.mockk.mockk

internal val testValidateNameUseCase = mockk<ValidateNameUseCase>()
internal val testAddPlayerUseCase = mockk<AddPlayerUseCase>(relaxed = true)
internal val firstPlayer = PlayerUI(1, "test")
internal val secondPlayer = PlayerUI(2, "test")

internal fun testViewModel(
    getAllPlayersUseCase: GetAllPlayersUseCase = mockk<GetAllPlayersUseCase>(relaxed = true),
    validateNameUseCase: ValidateNameUseCase = mockk<ValidateNameUseCase>(relaxed = true),
    addPlayerUseCase: AddPlayerUseCase = mockk<AddPlayerUseCase>(relaxed = true),
) = ChoosePlayerViewModel(
    getAllPlayersUseCase = getAllPlayersUseCase,
    validateNameUseCase = validateNameUseCase,
    addPlayerUseCase = addPlayerUseCase,
)