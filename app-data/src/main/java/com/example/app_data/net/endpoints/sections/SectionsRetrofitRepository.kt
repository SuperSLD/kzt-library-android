package com.example.app_data.net.endpoints.sections

import com.example.app_domain.datacontracts.net.SectionsNetRepository
import com.example.app_domain.models.sections.Section
import retrofit2.Retrofit

class SectionsRetrofitRepository(
    retrofit: Retrofit,
) : SectionsNetRepository {

    private val service by lazy { retrofit.create(SectionsRetrofitService::class.java) }

    override suspend fun get(): List<Section> {
        return service.get().dataOrThrow()!!
    }
}
