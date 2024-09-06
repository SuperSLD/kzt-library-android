package com.example.rnsonboarding.di

import com.example.app_data.di.provideConfigModules
import com.example.app_data.di.provideNetModules
import com.example.app_domain.di.provideControllers
import com.example.app_domain.di.provideDomainModules
import com.example.rnsonboarding.BuildConfig
import org.koin.dsl.module

fun appModule() = module {
//    provideDataFlow()
    provideControllers()
    provideConfigModules()
    provideDomainModules()
    provideNetModules(BuildConfig.SERVER_URL)
}