package com.example.app_domain.usecases.user

import com.example.app_domain.datacontracts.config.ConfigKey
import com.example.app_domain.datacontracts.config.ConfigRepository

class SetPinCodeUseCase(
    private val configRepository: ConfigRepository,
) {

    operator fun invoke(pin: String) =
        configRepository.putValue(ConfigKey.PIN_CODE, pin)
}