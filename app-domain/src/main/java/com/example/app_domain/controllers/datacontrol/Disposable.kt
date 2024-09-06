package com.example.app_domain.controllers.datacontrol

/**
 * Информация для возможности отписки от
 * контроллера данных.
 */
class Disposable(private val block: ()->Unit) {
    var isDisposed: Boolean = false

    fun dispose() {
        if (!isDisposed) {
            block()
            isDisposed = true
        }
    }
}