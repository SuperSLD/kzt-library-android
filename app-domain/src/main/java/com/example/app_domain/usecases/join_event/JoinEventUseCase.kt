package com.example.app_domain.usecases.join_event

import com.example.app_domain.datacontracts.net.JoinEventNetRepository

class JoinEventUseCase(
    private val joinEventNetRepository: JoinEventNetRepository,
) {
    suspend operator fun invoke(id: String) =
        joinEventNetRepository.joinEvent(id)
}