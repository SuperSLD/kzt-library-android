package com.example.app_ui.common.core.base

import android.app.Application
import androidx.annotation.CallSuper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

open class AppBase(private var appModule: Module): Application() {

    /**
     * Инициализация DI Koin и Timber
     */
    @CallSuper
    open override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppBase.baseContext)

            //твой выбор
            modules(appModule)

            //выбор экспертов
            modules(baseAppModule())
        }
    }
}