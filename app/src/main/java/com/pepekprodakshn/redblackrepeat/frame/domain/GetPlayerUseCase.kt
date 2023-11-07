package com.pepekprodakshn.redblackrepeat.frame.domain

import com.pepekprodakshn.redblackrepeat.choosePlayer.data.PlayersRepository
import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.toUI
import javax.inject.Inject

class GetPlayerUseCase @Inject constructor(
    private val repository: PlayersRepository,
) {

    suspend fun execute(id: Int) = repository.getPlayer(id).toUI()
}