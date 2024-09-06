package com.example.app_domain.datacontracts.config

import com.example.app_domain.datacontracts.config.ConfigKey

interface ConfigRepository {

    fun putValue(configKey: ConfigKey, value: String)

    fun getValue(configKey: ConfigKey): String
}