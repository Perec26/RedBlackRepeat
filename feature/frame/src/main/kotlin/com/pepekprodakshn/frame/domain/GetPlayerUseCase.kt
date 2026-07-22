package com.pepekprodakshn.frame.domain

import com.pepekprodakshn.frame.domain.mapper.toUI
import com.pepekprodakshn.player.dataSource.PlayersRepository
import javax.inject.Inject

internal class GetPlayerUseCase @Inject constructor(private val repository: PlayersRepository) {

    suspend fun execute(id: Int) = repository.getPlayer(id).toUI()
}
