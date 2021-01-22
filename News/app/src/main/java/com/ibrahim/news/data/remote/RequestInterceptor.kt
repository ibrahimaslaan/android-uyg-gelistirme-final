package com.ibrahim.news.data.remote

import com.ibrahim.news.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("country", Constants.COUNTRY)
            .addQueryParameter("apiKey", Constants.API_KEY)
            .build()
        val request = originalRequest.newBuilder().url(url).build()
        return  chain.proceed(request)
    }
}