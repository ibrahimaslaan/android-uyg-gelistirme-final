package com.ibrahim.news.data.remote

import com.ibrahim.news.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getApiService() : ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun getHttpClient() : OkHttpClient{
        val client = OkHttpClient.Builder()
        client.addInterceptor(RequestInterceptor())

        return client.build()
    }
}z