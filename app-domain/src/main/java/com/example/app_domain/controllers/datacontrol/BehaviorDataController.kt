package com.example.app_domain.controllers.datacontrol

class BehaviorDataController<T> : DataController<T>() {

    private val oldValues = mutableListOf<T>()

    override fun emit(value: T) {
        oldValues.add(value)
        super.emit(value)
    }

    override fun listen(callback: (T) -> Unit): Disposable {
        oldValues.forEach(callback)
        return super.listen(callback)
    }
}