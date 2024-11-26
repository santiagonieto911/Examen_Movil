package com.example.examen_movil.data.model

interface CovidRepository {
    suspend fun getCovidData(country: String): List<CovidData>
}