package com.pepekprodakshn.playerlist.domain

import com.pepekprodakshn.player.dataSource.PlayersRepository
import com.pepekprodakshn.playerlist.domain.mapper.toDomain
import com.pepekprodakshn.playerlist.ui.model.PlayerUI
import javax.inject.Inject

class AddPlayerUseCase @Inject constructor(
    private val repository: PlayersRepository,
) {

    suspend fun execute(playerUI: PlayerUI) = repository.addPlayer(playerUI.toDomain())
}