package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.constants.TaskConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var INSTANCE: Retrofit
        private var personKey = ""
        private var token = ""

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor { http ->
                val request = http.request()
                    .newBuilder()
                    .addHeader(TaskConstants.HEADER.TOKEN_KEY, token)
                    .addHeader(TaskConstants.HEADER.PERSON_KEY, personKey)
                    .build()

                http.proceed(request)
            }

            if (!::INSTANCE.isInitialized) {
                synchronized(RetrofitClient::class) {
                    INSTANCE = Retrofit.Builder().baseUrl(TaskConstants.RETROFIT.BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create()).build()
                }
            }
            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }

        fun addHeaders(tokenValue: String, personKeyValue: String) {
            token = tokenValue
            personKey = personKeyValue
        }
    }
}