package com.example.app_data.net.retrofit

import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {

    fun create(
        baseBackendUrl: String,
        vararg interceptors: Interceptor,
    ): Retrofit {
        val okHttpClient = createOkHttpClient(null, *interceptors)
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseBackendUrl)
            .addConverterFactory(ReturnNullOnEmptyBodyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createOkHttpClient(
        accessTokenExpiredAuthenticator: Authenticator?,
        vararg interceptors: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(SOCKET_TIMEOUT_EXCEPTION_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(SOCKET_TIMEOUT_EXCEPTION_IN_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(SOCKET_TIMEOUT_EXCEPTION_IN_SECONDS, TimeUnit.SECONDS)
            .cache(null)
            .apply {
                interceptors.forEach { addInterceptor(it) }
                accessTokenExpiredAuthenticator?.let { authenticator(it) }
            }
            .build()
    }
}

private const val SOCKET_TIMEOUT_EXCEPTION_IN_SECONDS: Long = 60
