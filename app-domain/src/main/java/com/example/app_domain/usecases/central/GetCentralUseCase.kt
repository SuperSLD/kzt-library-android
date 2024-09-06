package com.example.app_domain.usecases.central

import com.example.app_domain.datacontracts.net.CentralNetRepository

class GetCentralUseCase(
    private val centralNetRepository: CentralNetRepository,
) {

    suspend operator fun invoke() =
        centralNetRepository.get()
}