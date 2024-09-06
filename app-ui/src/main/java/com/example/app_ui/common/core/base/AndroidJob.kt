package com.example.app_ui.common.core.base

import androidx.lifecycle.LifecycleObserver
import kotlinx.coroutines.Job

/**
 * Остановка корутин при DESTROY.
 *
 * @author Solyanoy Leonid (solyanoy.leonid@gmail.com)
 */
class AndroidJob: Job by Job(), LifecycleObserver {

    /**
     * Остановка корутины когда получаем событие удаления.
     */
    fun destroy() {
        cancel()
    }
}