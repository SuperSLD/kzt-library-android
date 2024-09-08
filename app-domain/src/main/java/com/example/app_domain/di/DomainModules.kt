package com.example.app_domain.di

import com.example.app_domain.controllers.BottomVisibilityController
import com.example.app_domain.controllers.ChangeBottomTabController
import com.example.app_domain.controllers.NavigationController
import com.example.app_domain.controllers.PointTypeController
import com.example.app_domain.controllers.SelectMarkerController
import com.example.app_domain.controllers.SelectRoomController
import com.example.app_domain.usecases.book.GetBooksContentUseCase
import com.example.app_domain.usecases.book.OrderBookUseCase
import com.example.app_domain.usecases.book.RenewBookUseCase
import com.example.app_domain.usecases.central.GetCentralUseCase
import com.example.app_domain.usecases.courses.CheckUseCase
import com.example.app_domain.usecases.courses.FinishUseCase
import com.example.app_domain.usecases.courses.GetCoursesUseCase
import com.example.app_domain.usecases.documents.GetDocumentsContentUseCase
import com.example.app_domain.usecases.documents.SendRequestUseCase
import com.example.app_domain.usecases.join_event.JoinEventUseCase
import com.example.app_domain.usecases.user.GetPinCodeUseCase
import com.example.app_domain.usecases.user.GetProfileUseCase
import com.example.app_domain.usecases.user.IsAuthUseCase
import com.example.app_domain.usecases.user.LoginUseCase
import com.example.app_domain.usecases.user.PinCodeIsSetUseCase
import com.example.app_domain.usecases.user.RegistrationUseCase
import com.example.app_domain.usecases.user.SetPinCodeUseCase
import org.koin.core.module.Module

fun Module.provideDomainModules() {

    single { LoginUseCase(get(), get()) }
    single { RegistrationUseCase(get(), get()) }
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
    single { JoinEventUseCase(get()) }
    single { GetBooksContentUseCase(get()) }
    single { OrderBookUseCase(get()) }
    single { RenewBookUseCase(get()) }
}

fun Module.provideControllers() {
    single { BottomVisibilityController() }
    single { ChangeBottomTabController() }
    single { PointTypeController() }
    single { SelectRoomController() }
    single { SelectMarkerController() }
    single { NavigationController() }
}