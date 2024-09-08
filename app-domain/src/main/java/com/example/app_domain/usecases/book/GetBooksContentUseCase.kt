package com.example.app_domain.usecases.book

import com.example.app_domain.datacontracts.net.BookNetRepository

class GetBooksContentUseCase(
    private val bookNetRepository: BookNetRepository
) {

    suspend operator fun invoke() =
        bookNetRepository.getContent()
}