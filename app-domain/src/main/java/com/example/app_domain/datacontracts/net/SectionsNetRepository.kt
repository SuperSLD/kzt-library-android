package com.example.app_domain.datacontracts.net

import com.example.app_domain.models.sections.Section

interface SectionsNetRepository {

    suspend fun get(): List<Section>
}