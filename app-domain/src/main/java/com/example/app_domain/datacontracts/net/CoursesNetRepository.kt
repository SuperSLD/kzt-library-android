package com.example.app_domain.datacontracts.net

import com.example.app_domain.models.courses.Course

interface CoursesNetRepository {

    suspend fun get(): List<Course>

    suspend fun check(id: String)

    suspend fun finish(id: String)
}