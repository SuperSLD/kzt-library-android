package com.example.app_domain.usecases.courses

import com.example.app_domain.datacontracts.net.CoursesNetRepository

class CheckUseCase(
    private val coursesNetRepository: CoursesNetRepository,
) {

    suspend operator fun invoke(id: String) =
        coursesNetRepository.check(id)
}