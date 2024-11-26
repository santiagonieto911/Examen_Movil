package com.example.examen_movil.data.model

import com.example.examen_movil.data.model.CovidRepository

class CovidRepositoryImpl(private val apiService: CovidApiService) : CovidRepository {
    override suspend fun getCovidData(country: String): List<CovidData> {
        return apiService.getCovidData(country)
    }
}