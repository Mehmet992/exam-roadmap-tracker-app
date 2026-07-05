package com.example.examroadmaptrackerapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ExamApiService {
    @GET("api/announcements") // Assuming this is your endpoint
    suspend fun getAnnouncements(): List<DashboardItem>
}

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/" // Special IP for host's localhost

    val instance: ExamApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExamApiService::class.java)
    }
}
