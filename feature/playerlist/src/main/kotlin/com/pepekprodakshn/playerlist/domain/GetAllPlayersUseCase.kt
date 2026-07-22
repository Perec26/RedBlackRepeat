package com.pepekprodakshn.playerlist.domain

import com.pepekprodakshn.player.dataSource.PlayersRepository
import com.pepekprodakshn.playerlist.domain.mapper.toUI
import javax.inject.Inject

internal class GetAllPlayersUseCase @Inject constructor(
    private val repository: PlayersRepository,
) {

    suspend fun execute() = repository.getAllPlayers().toUI()
}
