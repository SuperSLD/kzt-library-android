package com.example.app_data.net.endpoints.join_event

import com.example.app_domain.datacontracts.net.JoinEventNetRepository
import retrofit2.Retrofit

class JoinEventRetrofitRepository(
    retrofit: Retrofit,
): JoinEventNetRepository {

    private val service by lazy { retrofit.create(JoinEventRetrofitService::class.java) }
    override suspend fun joinEvent(id: String) {
        service.joinEvent(id)
    }
}