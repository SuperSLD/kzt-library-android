package com.example.app_domain.usecases.sections

import com.example.app_domain.datacontracts.net.SectionsNetRepository

class GetSectionsUseCase(
    private val netRep: SectionsNetRepository,
) {

    suspend operator fun invoke() = netRep.get()
}