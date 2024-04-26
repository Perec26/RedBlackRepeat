package com.pepekprodakshn.redblackrepeat.choosePlayer.domain

import com.pepekprodakshn.frame.ui.PlayerUI
import com.pepekprodakshn.player.dataSource.PlayersRepository
import javax.inject.Inject

class AddPlayerUseCase @Inject constructor(
    private val repository: PlayersRepository,
) {

    suspend fun execute(playerUI: PlayerUI) = repository.addPlayer(playerUI.toDomain())
}