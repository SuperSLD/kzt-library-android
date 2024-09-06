package com.example.app_domain.controllers.datacontrol

/**
 * Информация для возможности отписки от
 * контроллера данных.
 */
class CompositeDisposable {
    var isDisposed: Boolean = false
    private var disposables = mutableListOf<Disposable>()

    fun add(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun remove(disposable: Disposable) {
        disposables.remove(disposable)
    }

    fun size() = disposables.size

    fun clear() = disposables.clear()

    fun dispose() {
        if (!isDisposed) {
            disposables.forEach { it.dispose() }
            isDisposed = true
        }
    }
}