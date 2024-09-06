package com.example.app_data.di

import com.example.data.config.ConfigPreferenceRepository
import com.example.app_domain.datacontracts.config.ConfigRepository
import org.koin.core.module.Module

fun Module.provideConfigModules() {

    single<ConfigRepository> {
        ConfigPreferenceRepository(get())
    }
}