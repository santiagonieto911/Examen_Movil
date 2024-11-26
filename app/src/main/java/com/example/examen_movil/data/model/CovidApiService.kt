package com.example.examen_movil.data.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Headers

interface CovidApiService {
    @Headers("X-Api-Key: wLVPN1zV08lJYF7uXqgyPw==zVwp6TlVcAO1NLUf")
    @GET("covid19")
    suspend fun getCovidData(
        @Query("country") country: String
    ): List<CovidData>

    companion object {
        private const val BASE_URL = "https://api.api-ninjas.com/v1/"

        fun create(): CovidApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CovidApiService::class.java)
        }
    }
}