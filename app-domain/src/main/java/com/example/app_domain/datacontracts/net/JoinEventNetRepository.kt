package com.example.app_domain.datacontracts.net

interface JoinEventNetRepository {

    suspend fun joinEvent(id: String)
}