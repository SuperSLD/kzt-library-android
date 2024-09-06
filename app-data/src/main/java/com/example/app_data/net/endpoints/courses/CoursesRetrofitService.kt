package com.example.app_data.net.endpoints.courses

import com.example.app_data.net.endpoints.DataWrapper
import com.example.app_domain.models.courses.Course
import retrofit2.http.GET
import retrofit2.http.Path

interface CoursesRetrofitService {

    @GET("courses/get")
    suspend fun get(): DataWrapper<List<Course>>

    @GET("courses/check/{id}")
    suspend fun check(@Path("id") id: String) : DataWrapper<Int>

    @GET("courses/finished/{id}")
    suspend fun finish(@Path("id") id: String) : DataWrapper<Int>
}
