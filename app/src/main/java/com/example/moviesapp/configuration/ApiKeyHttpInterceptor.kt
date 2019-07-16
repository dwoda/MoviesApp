package com.example.moviesapp.configuration

import com.example.moviesapp.api.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyHttpInterceptor @Inject constructor(private val apiConstants: ApiConstants) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request
            .url()
            .newBuilder()
            .addQueryParameter("api_key", apiConstants.apiKey)
            .build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}