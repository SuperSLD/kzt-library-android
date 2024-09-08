package com.example.app_domain.usecases.book

import com.example.app_domain.datacontracts.net.BookNetRepository

class OrderBookUseCase(
    private val bookNetRepository: BookNetRepository
) {
    suspend operator fun invoke(id: String) =
        bookNetRepository.orderBook(id)
}