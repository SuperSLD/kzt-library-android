package com.example.app_domain.usecases.documents

import com.example.app_domain.datacontracts.net.DocumentsNetRepository

class GetDocumentsContentUseCase(
    private val documentsNetRepository: DocumentsNetRepository,
) {

    suspend operator fun invoke() =
        documentsNetRepository.getContent()
}