package com.example.app_ui.common.core.base

import org.koin.dsl.module

fun baseAppModule() = module {
    single {
        CiceroneHolder()
    }
}