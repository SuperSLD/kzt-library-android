package com.example.app_domain.controllers.datacontrol

/**
 * Базовый интерфейс контроллера данных
 */
abstract class DataController<T> {

    private val listeners = mutableListOf<(T) -> Unit>()

    /**
     * Отправка значения в контроллер.
     */
    open fun emit(value: T) {
        listeners.forEach { it(value) }
    }

    /**
     * Подписка на события контроллера.
     */
    open fun listen(callback: (T) -> Unit): Disposable {
        listeners.add(callback)
        return Disposable {
            listeners.remove(callback)
        }
    }
}