package com.example.app_domain.usecases.documents

import com.example.app_domain.datacontracts.net.DocumentsNetRepository

class SendRequestUseCase(
    private val documentsNetRepository: DocumentsNetRepository,
) {

    suspend operator fun invoke(title: String) {
        documentsNetRepository.sendRequest(title)
    }
}