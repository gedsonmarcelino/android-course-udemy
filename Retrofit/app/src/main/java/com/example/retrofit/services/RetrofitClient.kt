package com.example.retrofit.services

import com.example.retrofit.utils.AppConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var instance: Retrofit

        private fun getInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Retrofit.Builder()
                        .client(httpClient.build())
                        .baseUrl(AppConfig.ServiceJson.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return instance
            }
        }

        fun <T> createService(serviceClass: Class<T>): T {
            return getInstance().create(serviceClass)
        }
    }
}