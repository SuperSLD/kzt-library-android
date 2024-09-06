package com.example.app_domain.di

import com.example.app_domain.controllers.*
import com.example.app_domain.usecases.central.GetCentralUseCase
import com.example.app_domain.usecases.courses.CheckUseCase
import com.example.app_domain.usecases.courses.FinishUseCase
import com.example.app_domain.usecases.courses.GetCoursesUseCase
import com.example.app_domain.usecases.documents.GetDocumentsContentUseCase
import com.example.app_domain.usecases.documents.SendRequestUseCase
import com.example.app_domain.usecases.user.*
import org.koin.core.module.Module

fun Module.provideDomainModules() {

    single { LoginUseCase(get(), get()) }
    single { IsAuthUseCase(get()) }
    single { PinCodeIsSetUseCase(get()) }
    single { GetPinCodeUseCase(get()) }
    single { SetPinCodeUseCase(get()) }
    single { GetProfileUseCase(get()) }
    single { GetDocumentsContentUseCase(get()) }
    single { SendRequestUseCase(get()) }
    single { GetCoursesUseCase(get()) }
    single { GetCentralUseCase(get()) }
    single { CheckUseCase(get()) }
    single { FinishUseCase(get()) }
}

fun Module.provideControllers() {
    single { BottomVisibilityController() }
    single { ChangeBottomTabController() }
    single { PointTypeController() }
    single { SelectRoomController() }
    single { SelectMarkerController() }
    single { NavigationController() }
}