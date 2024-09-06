package com.example.app_domain.usecases.user

import com.example.app_domain.datacontracts.config.ConfigKey
import com.example.app_domain.datacontracts.config.ConfigRepository

class IsAuthUseCase(
    private val configRepository: ConfigRepository,
) {

    operator fun invoke() =
        configRepository.getValue(ConfigKey.AUTH_TOKEN).length > 5
}