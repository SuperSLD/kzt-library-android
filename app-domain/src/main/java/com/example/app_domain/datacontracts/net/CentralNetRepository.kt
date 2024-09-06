package com.example.app_domain.datacontracts.net

import com.example.app_domain.models.central.Central

interface CentralNetRepository {

    suspend fun get(): Central
}