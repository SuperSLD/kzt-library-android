package com.example.app_domain.usecases.user

import com.example.app_domain.datacontracts.net.UserNetRepository

class GetProfileUseCase(
    private val userNetRepository: UserNetRepository,
) {

    suspend operator fun invoke() = userNetRepository.getInfo()
}