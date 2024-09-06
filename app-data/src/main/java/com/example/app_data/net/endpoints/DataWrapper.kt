package com.example.app_data.net.endpoints

import java.lang.Exception

/**
 * Общая модель ответа сервера.
 * [T] тип основной части ответа.
 */
data class DataWrapper<T> (
    var success: Boolean = false,
    var message: String = "",
    var data: T? = null
) {

    fun dataOrThrow() = if (success) data else throw ServerException(message)
}

class ServerException(message: String) : Exception(message)