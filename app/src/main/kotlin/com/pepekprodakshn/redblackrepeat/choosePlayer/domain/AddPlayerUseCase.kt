package com.pepekprodakshn.redblackrepeat.choosePlayer.domain

import com.pepekprodakshn.player.dataSource.PlayersRepository
import com.pepekprodakshn.redblackrepeat.frame.ui.PlayerUI
import javax.inject.Inject

class AddPlayerUseCase @Inject constructor(
    private val repository: PlayersRepository,
) {

    suspend fun execute(playerUI: PlayerUI) = repository.addPlayer(playerUI.toDomain())
}