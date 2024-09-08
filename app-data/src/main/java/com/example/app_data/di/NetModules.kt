package com.example.app_data.di

import com.example.app_data.net.endpoints.book.BookRetrofitRepository
import com.example.app_data.net.endpoints.central.CentralRetrofitRepository
import com.example.app_data.net.endpoints.courses.CoursesRetrofitRepository
import com.example.app_data.net.endpoints.documents.DocumentsRetrofitRepository
import com.example.app_data.net.endpoints.join_event.JoinEventRetrofitRepository
import com.example.app_data.net.endpoints.sections.SectionsRetrofitRepository
import com.example.app_data.net.endpoints.user.UserRetrofitRepository
import com.example.app_data.net.retrofit.AuthTokenInterceptor
import com.example.app_data.net.retrofit.RetrofitFactory
import com.example.app_domain.datacontracts.net.BookNetRepository
import com.example.app_domain.datacontracts.net.CentralNetRepository
import com.example.app_domain.datacontracts.net.CoursesNetRepository
import com.example.app_domain.datacontracts.net.DocumentsNetRepository
import com.example.app_domain.datacontracts.net.JoinEventNetRepository
import com.example.app_domain.datacontracts.net.SectionsNetRepository
import com.example.app_domain.datacontracts.net.UserNetRepository
import okhttp3.Interceptor
import org.koin.core.module.Module

fun Module.provideNetModules(baseUrl: String) {

    provideRetrofit(baseUrl)

    single<UserNetRepository> {
        UserRetrofitRepository(get())
    }

    single<DocumentsNetRepository> {
        DocumentsRetrofitRepository(get())
    }

    single<CoursesNetRepository> {
        CoursesRetrofitRepository(get())
    }

    single<CentralNetRepository> {
        CentralRetrofitRepository(get())
    }

    single<JoinEventNetRepository> {
        JoinEventRetrofitRepository(get())
    }

    single<SectionsNetRepository> {
        SectionsRetrofitRepository(get())
    }

    single<BookNetRepository> {
        BookRetrofitRepository(get())
    }
}


private fun Module.provideRetrofit(
    baseBackendUrl: String,
) {
    single {
        createRetrofit(
            baseBackendUrl,
            AuthTokenInterceptor(get()),
        )
    }
}

private fun createRetrofit(
    baseBackendUrl: String,
    vararg interceptors: Interceptor,
) = RetrofitFactory().create(
    baseBackendUrl,
    *interceptors,
)
