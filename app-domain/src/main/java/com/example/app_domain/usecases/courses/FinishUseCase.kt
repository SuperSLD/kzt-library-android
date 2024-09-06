package com.example.app_domain.usecases.courses

import com.example.app_domain.datacontracts.net.CoursesNetRepository

class FinishUseCase(
    private val coursesNetRepository: CoursesNetRepository,
) {

    suspend operator fun invoke(id: String) =
        coursesNetRepository.finish(id)
}