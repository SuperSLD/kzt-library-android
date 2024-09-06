package com.example.app_data.net.endpoints.courses

import com.example.app_domain.datacontracts.net.CoursesNetRepository
import com.example.app_domain.models.courses.Course
import retrofit2.Retrofit

class CoursesRetrofitRepository(
    retrofit: Retrofit,
) : CoursesNetRepository {

    private val service by lazy { retrofit.create(CoursesRetrofitService::class.java) }

    override suspend fun get(): List<Course> {
        return service.get().dataOrThrow()!!
    }

    override suspend fun check(id: String) {
        service.check(id)
    }

    override suspend fun finish(id: String) {
        service.finish(id)
    }
}
