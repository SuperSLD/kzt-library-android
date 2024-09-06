package com.example.app_domain.usecases.user

import com.example.app_domain.datacontracts.config.ConfigKey
import com.example.app_domain.datacontracts.config.ConfigRepository
import com.example.app_domain.datacontracts.net.UserNetRepository

class LoginUseCase(
    private val userNetRepository: UserNetRepository,
    private val configRepository: ConfigRepository,
) {

    suspend operator fun invoke(login: String): String {
        val token = userNetRepository.login(login)
        configRepository.putValue(ConfigKey.AUTH_TOKEN, token)
        return token
    }
}