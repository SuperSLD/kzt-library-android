package com.example.app_data.net.endpoints.user

import com.example.app_domain.datacontracts.net.UserNetRepository
import com.example.app_domain.models.user.User
import com.example.data.net.endpoints.user.LoginRequest
import retrofit2.Retrofit

class UserRetrofitRepository(
    retrofit: Retrofit,
) : UserNetRepository {

    private val service by lazy { retrofit.create(UserRetrofitService::class.java) }

    override suspend fun login(login: String): String {
        return service.login(LoginRequest(login)).dataOrThrow()!!.token
    }

    override suspend fun getInfo(): User {
        return service.getInfo().dataOrThrow()!!
    }
}
