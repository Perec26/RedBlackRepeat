package com.pepekprodakshn.redblackrepeat.choosePlayer.domain

import com.pepekprodakshn.redblackrepeat.choosePlayer.data.PlayersRepository
import javax.inject.Inject

class GetAllPlayersUseCase @Inject constructor(
    private val repository: PlayersRepository,
) {

    suspend fun execute() = repository.getAllPlayers().toUI()

}