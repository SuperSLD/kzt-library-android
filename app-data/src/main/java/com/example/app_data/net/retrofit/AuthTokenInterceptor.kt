package com.example.app_data.net.retrofit

import android.annotation.SuppressLint
import com.example.app_domain.datacontracts.config.ConfigKey
import com.example.app_domain.datacontracts.config.ConfigRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class AuthTokenInterceptor(
    private val configRepository: ConfigRepository,
) : Interceptor {

    @SuppressLint("HardwareIds")
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        var request: Request = original

        request = original.newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .header("Authorization", configRepository.getValue(ConfigKey.AUTH_TOKEN))
            .method(original.method(), original.body())
            .build()

        return chain.proceed(request)
    }
}