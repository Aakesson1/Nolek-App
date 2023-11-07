package com.example.nolekapp.Database

import LeakTestApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constants {
    const val APP_ID = "testapplication-ischo"
}
object RetrofitClient {
    private const val BASE_URL = "https://localhost:7225/api/LeakTests/{id}"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: LeakTestApiService by lazy {
        retrofit.create(LeakTestApiService::class.java)
    }
}