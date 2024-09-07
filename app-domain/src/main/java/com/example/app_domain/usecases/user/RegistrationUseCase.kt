package com.example.app_domain.usecases.user

import com.example.app_domain.datacontracts.config.ConfigKey
import com.example.app_domain.datacontracts.config.ConfigRepository
import com.example.app_domain.datacontracts.net.UserNetRepository

class RegistrationUseCase(
    private val userNetRepository: UserNetRepository,
    private val configRepository: ConfigRepository,
) {
    suspend operator fun invoke(
        login: String,
        name: String,
        lastName: String,
        midName: String,
        avatar: String?
    ): String {
        val token = userNetRepository.register(login, name, lastName, midName, avatar)
        configRepository.putValue(ConfigKey.AUTH_TOKEN, token)
        return token
    }
}